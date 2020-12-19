package com.technibyte.milestokm.ui.math_behind

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MathBehindViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is math_behind Fragment"
    }
    val text: LiveData<String> = _text
}