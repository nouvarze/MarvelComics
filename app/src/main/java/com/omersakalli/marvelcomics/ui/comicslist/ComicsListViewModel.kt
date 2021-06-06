package com.omersakalli.marvelcomics.ui.comicslist

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omersakalli.marvelcomics.data.Repository
import com.omersakalli.marvelcomics.ui.model.Comic
import com.omersakalli.marvelcomics.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var comics: MutableLiveData<ArrayList<Comic>> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
    var navigateToErrorPage: SingleLiveData<String> = SingleLiveData()

    fun fetchComics() {
        progressBarVisibility.value = View.VISIBLE
        viewModelScope.launch(Dispatchers.IO) {
            val comicsResult = repository.getComics()
            if(comicsResult.isSuccessful){
                comics.postValue(ArrayList(comicsResult.variable))
            } else {
                navigateToErrorPage.postValue(comicsResult.errorMessage ?: "Error")
            }
            progressBarVisibility.postValue(View.GONE)
        }
    }

    //No progressbar since it is only called from swipe refresh and that has its own thing
    fun fetchComicsFromNetwork(){
        viewModelScope.launch(Dispatchers.IO) {
            val comicsResult = repository.getComicsFromNetwork()
            if(comicsResult.isSuccessful){
                comics.postValue(ArrayList(comicsResult.variable))
            } else {
                navigateToErrorPage.postValue(comicsResult.errorMessage ?: "Error")
            }
        }
    }
}