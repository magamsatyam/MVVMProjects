package com.satya.mvvm.data

import com.satya.mvvm.data.remote.NetworkData
import com.satya.mvvm.model.Acronyms
import com.satya.mvvm.model.AcronymsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by Satya
 */

class DataRepository @Inject constructor(private val remoteRepository: NetworkData, private val ioDispatcher: CoroutineContext) : DataRepositorySource {

    override suspend fun getAcronyms(acronym: String): Flow<Resource<List<Acronyms>>> {
        return flow {
            emit(remoteRepository.getAcronyms(acronym))
        }.flowOn(ioDispatcher)
    }



}
