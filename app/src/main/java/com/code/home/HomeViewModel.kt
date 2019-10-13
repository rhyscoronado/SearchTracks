package com.code.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.code.base.BaseViewModel
import com.code.model.SongDetail
import com.code.model.dao.SongDetailDao
import com.code.network.SearchApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class HomeViewModel(var songDetailDao: SongDetailDao) : BaseViewModel() {

    /**
     * We Inject API from BaseViewModel that we also set
     * from fragment home view model factory
     */
    @Inject
    lateinit var searchApi: SearchApi

    val trackListAdapter: TracksAdapter = TracksAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val songDetailList: MutableLiveData<ArrayList<SongDetail>> = MutableLiveData()

    private lateinit var subscription: Disposable

    /**
     * We dispose the subscription to avoid any memory leak on
     * removal of fragment
     */
    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) {
            subscription.dispose()
        }
    }

    /**
     * Initialisation of viewmodel
     */
    init {
        loadTracks()
    }

    /**
     * This method is private since it is being called from it's own class
     * now, how does it get called? refer to home fragment onViewCreated
     * once it gets instantiated it automatically calls init which calls this method
     */

    /**
     * We use subscription to handle threading, in this case we use rxjava2
     *
     * From the first hand we use `Observable.fromCallable` to make the method from
     * `SongDetailDao` which is `songDetailDao.all` to be a Observable object that we can subscribe.
     *
     * Then we use concatMap to handle the value that `songDetailDao.all` method returns,
     * from that point on we called the `songDetailDao.all` from the database checking if we
     * have a stored tracks inside the device.
     *
     * Then in concatMap we use a condition to check if the returned value is empty or not, if it is empty
     * we call the api and also use concatMap to insert the result of the api to the database.
     *
     * Then we use Observable.just to create an observable object based on the item that we supplied.
     *
     * Then we subscribe it to Schedulers.io which is meant to be for input/output bound thread.
     * We observe it from the mainThread.
     * We also specify what will happen when the method is commencing by doing `doOnSubscribe`
     * same goes with `doOnTerminate` when the whole process is done.
     *
     * Overall we use subscribe method to handle the item emitted by the Observable object
     * that we started, we also handle the error if there's any
     */
    private fun loadTracks() {
        subscription = Observable.fromCallable { songDetailDao.all }
            .concatMap { songList ->
                if (songList.isEmpty()) {
                    searchApi.getTracks().concatMap { songDetailList ->
                        songDetailDao.insertAll(songDetailList.results)
                        Observable.just(songDetailList)
                    }
                } else {
                    Observable.just(songList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingVisibility.value = View.VISIBLE }
            .doOnTerminate { loadingVisibility.value = View.GONE }
            .subscribe({ songDetailList ->
                onRetrieveSuccess(songDetailList as ArrayList<SongDetail>)
            }, {throwable ->
                onRetrieveError(throwable.localizedMessage)
            })
    }

    private fun onRetrieveSuccess(songDetailList: ArrayList<SongDetail>) {
        this.songDetailList.value = songDetailList
    }

    private fun onRetrieveError(localizedMessage: String) {
        loadingVisibility.value = View.GONE
        errorMessage.value = localizedMessage
    }

    fun setAdapterclickListener(trackClickListener: TrackClickListener) {
        trackListAdapter.setTrackClickListener(trackClickListener)
    }
}