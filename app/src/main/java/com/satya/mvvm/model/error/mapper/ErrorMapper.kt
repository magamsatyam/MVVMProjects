package com.satya.mvvm.model.error.mapper

import android.content.Context
import com.satya.mvvm.R
import com.satya.mvvm.model.error.NETWORK_ERROR
import com.satya.mvvm.model.error.NO_INTERNET_CONNECTION
import com.satya.mvvm.model.error.SEARCH_ERROR
import com.task.data.error.mapper.ErrorMapperSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) : ErrorMapperSource {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(SEARCH_ERROR, getErrorString(R.string.search_error))
        ).withDefault { getErrorString(R.string.network_error) }
}
