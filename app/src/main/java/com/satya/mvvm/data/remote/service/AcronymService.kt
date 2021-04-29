package com.satya.mvvm.data.remote.service

import com.satya.mvvm.model.acronym.AcronymItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("/software/acromine/dictionary.py")
    suspend fun fetchAcronyms(@Query("sf") acronym: String): Response<ArrayList<AcronymItem>>

}