package com.satya.mvvm.data.remote

import com.satya.mvvm.data.Resource
import com.satya.mvvm.data.remote.service.AcronymService
import com.satya.mvvm.model.Acronyms
import com.satya.mvvm.model.AcronymsItem
import com.satya.mvvm.model.error.NETWORK_ERROR
import com.satya.mvvm.model.error.NO_INTERNET_CONNECTION
import com.task.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.reflect.KSuspendFunction1


/**
 * Created by Satya
 */

class NetworkData @Inject
constructor(private val serviceGenerator: APITask, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun requestRecipes(): Resource<Acronyms> {
        val acronymService = serviceGenerator.createService(AcronymService::class.java)
        return when (val response = processCall(acronymService::fetchAcronyms)) {
            is List<*> -> {
                Resource.Success(data = response as Acronyms)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: KSuspendFunction1<String, Response<List<AcronymsItem>>>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke(String())
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
