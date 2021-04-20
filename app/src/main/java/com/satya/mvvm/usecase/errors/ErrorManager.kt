package com.task.usecase.errors

import com.satya.mvvm.model.error.Error
import com.satya.mvvm.model.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by Satya
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
