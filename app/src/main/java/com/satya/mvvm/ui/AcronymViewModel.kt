package com.satya.mvvm.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.satya.mvvm.data.DataRepositorySource
import com.satya.mvvm.data.Resource
import com.satya.mvvm.model.Acronyms
import com.satya.mvvm.model.AcronymsItem
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
    val _acronymData = MutableLiveData<Resource<Acronyms>>()
    val acronymData: LiveData<Resource<Acronyms>> get() = _acronymData


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


    fun getAcronyms(acronym: String){
         viewModelScope.launch {
             _acronymData.value = Resource.Loading()
             if(acronym.toString().length >= 2){
             wrapEspressoIdlingResource {
                 dataRepositorySource.getAcronyms(acronym).collect {
                     _acronymData.value = it
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
