package com.satya.mvvm.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.satya.mvvm.data.DataRepositorySource
import com.satya.mvvm.data.Resource
import com.satya.mvvm.model.Acronyms
import com.satya.mvvm.model.AcronymsItem
import com.satya.mvvm.ui.BaseViewModel
import com.task.utils.SingleEvent
import com.task.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(private val dataRepositorySource: DataRepositorySource): BaseViewModel() {

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val _acronymData = MutableLiveData<Resource<List<Acronyms>>>()
    val acronymData: LiveData<Resource<List<Acronyms>>> get() = _acronymData

    //TODO check to make them as one Resource
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val _acronymFound: MutableLiveData<AcronymsItem> = MutableLiveData()
    val acronymFound: LiveData<AcronymsItem> get() = _acronymFound

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val _showToast = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = _showToast

    private val _buttonClicked = MutableLiveData<Boolean>()

    val buttonClicked: LiveData<Boolean>
      get() = _buttonClicked

    val editTextContent = ObservableField<String>()

    fun getAcronyms(){
        Log.d("viewmodel","getAcronyms() called=="+editTextContent.get())
         viewModelScope.launch {
             _acronymData.value = Resource.Loading()
             if( editTextContent.toString().length >= 2){
             wrapEspressoIdlingResource {
                 editTextContent.get()?.let {
                     dataRepositorySource.getAcronyms(it).collect {
                         _acronymData.value = it
                     }
                 }
             }
         }
     }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        _showToast.value = SingleEvent(error.description)
    }

}


