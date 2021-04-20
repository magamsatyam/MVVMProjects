package com.satya.mvvm.data

import com.satya.mvvm.model.Acronyms
import kotlinx.coroutines.flow.Flow

/**
 * Created by Satya
 */

interface DataRepositorySource {
    suspend fun getAcronyms(acronym: String): Flow<Resource<Acronyms>>
}
