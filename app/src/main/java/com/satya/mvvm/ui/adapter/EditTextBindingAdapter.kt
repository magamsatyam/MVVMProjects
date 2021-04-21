package com.satya.mvvm.ui.adapter

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

object EditTextBindingAdapter {

    @BindingAdapter("textChangedListener")
    @JvmStatic fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher?) {
        editText.addTextChangedListener(textWatcher)
    }
}