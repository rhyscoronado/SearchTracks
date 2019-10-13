package com.code.network

import com.code.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface SearchApi {

    @GET("/search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun getTracks(): Observable<SearchResponse>
}