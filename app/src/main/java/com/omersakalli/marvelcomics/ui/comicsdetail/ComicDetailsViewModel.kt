package com.omersakalli.marvelcomics.ui.comicsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omersakalli.marvelcomics.ui.model.Comic

class ComicDetailsViewModel : ViewModel() {
    var comic: MutableLiveData<Comic> = MutableLiveData()
}