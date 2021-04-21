package com.satya.mvvm.viewmodel

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class EditTextObserver : BaseObservable() {

    @Bindable
    var acronym = ""
    set(value)  {
        field = value
        notifyChange()
    }

    @Bindable
    val acronymTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // Do nothing.
        }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            acronym = s.toString()
        }
        override fun afterTextChanged(s: Editable) {
            // Do nothing.
        }
    }
}