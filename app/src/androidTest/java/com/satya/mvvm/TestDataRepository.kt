package com.satya.mvvm

import com.satya.mvvm.TestUtil.dataStatus
import com.satya.mvvm.TestUtil.initData
import com.satya.mvvm.data.DataRepositorySource
import com.satya.mvvm.data.Resource
import com.satya.mvvm.model.acronym.AcronymItem
import com.satya.mvvm.model.error.NETWORK_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Created by Satya
 */

class TestDataRepository @Inject constructor() : DataRepositorySource {
    override suspend fun getAcronyms(acronym: String): Flow<Resource<ArrayList<AcronymItem>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.Success(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.DataError<AcronymItem>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.Success(AcronymItem(arrayListOf(), listOf()))) }
            }
        }
    }


}
