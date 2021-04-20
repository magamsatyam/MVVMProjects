package com.task.usecase.errors

import com.satya.mvvm.model.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
