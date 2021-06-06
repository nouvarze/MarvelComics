package com.omersakalli.marvelcomics.ui.errorscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ErrorViewModel : ViewModel() {
    var errorText: MutableLiveData<String> = MutableLiveData()
}