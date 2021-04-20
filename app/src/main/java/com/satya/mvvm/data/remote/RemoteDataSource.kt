package com.satya.mvvm.data.remote

import com.satya.mvvm.data.Resource
import com.satya.mvvm.model.Acronyms

/**
 * Created by Satya
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Acronyms>
}
