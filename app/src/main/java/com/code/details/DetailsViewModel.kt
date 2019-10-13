package com.code.details

import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.code.base.BaseViewModel
import com.code.model.SongDetail


class DetailsViewModel : BaseViewModel() {

    private val trackId = MutableLiveData<Int>()
    private val name = MutableLiveData<String>()
    private val genre = MutableLiveData<String>()
    private val artwork = MutableLiveData<String>()
    private val price = MutableLiveData<Double>()
    private val longDescription = MutableLiveData<String>()

    fun bind(song: SongDetail) {
        trackId.value = song.id
        name.value = song.trackName
        genre.value = song.primaryGenreName
        artwork.value = song.artworkUrl30
        price.value = song.trackPrice
        longDescription.value = song.longDescription

    }


}