package com.satya.mvvm.data.remote

import com.satya.mvvm.data.Resource
import com.satya.mvvm.model.Acronyms
import com.satya.mvvm.model.AcronymsItem

/**
 * Created by Satya
 */

internal interface RemoteDataSource {
    suspend fun getAcronyms(acronym: String): Resource<List<Acronyms>>
}
