package com.code.model

import com.google.gson.annotations.SerializedName

class SearchResponse {

    @SerializedName("resultCount")
    var resultCount: Int = 0

    @SerializedName("results")
    var results: ArrayList<SongDetail> = ArrayList()
}