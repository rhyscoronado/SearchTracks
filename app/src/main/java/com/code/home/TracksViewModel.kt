package com.code.home

import androidx.lifecycle.MutableLiveData
import com.code.base.BaseViewModel
import com.code.model.SongDetail

class TracksViewModel : BaseViewModel() {

    private val trackId = MutableLiveData<Int>()
    private val name = MutableLiveData<String>()
    private val genre = MutableLiveData<String>()
    private val artwork = MutableLiveData<String>()
    private val price = MutableLiveData<Double>()

    fun bind(song: SongDetail) {
        trackId.value = song.id
        name.value = song.trackName
        genre.value = song.primaryGenreName
        artwork.value = song.artworkUrl30
        price.value = song.trackPrice

    }


}