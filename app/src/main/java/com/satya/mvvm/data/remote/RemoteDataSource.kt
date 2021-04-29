package com.satya.mvvm.data.remote

import com.satya.mvvm.model.acronym.AcronymItem
import com.satya.mvvm.data.Resource

/**
 * Created by Satya
 */

 internal interface RemoteDataSource {
    suspend fun getAcronyms(acronym: String): Resource<ArrayList<AcronymItem>>
}
