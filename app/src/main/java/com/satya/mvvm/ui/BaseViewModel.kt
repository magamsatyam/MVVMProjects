package com.satya.mvvm.ui

import androidx.lifecycle.ViewModel
import com.task.usecase.errors.ErrorManager
import javax.inject.Inject


/**
 * Created by Satya
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}
