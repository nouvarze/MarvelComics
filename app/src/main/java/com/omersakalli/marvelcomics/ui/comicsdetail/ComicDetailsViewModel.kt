package com.omersakalli.marvelcomics.ui.comicsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.omersakalli.marvelcomics.data.Repository
import com.omersakalli.marvelcomics.data.model.ComicDetailsResponse
import com.omersakalli.marvelcomics.ui.model.Comic

class ComicDetailsViewModel : ViewModel() {
    var comic: MutableLiveData<Comic> = MutableLiveData()
}