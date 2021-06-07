package com.omersakalli.marvelcomics.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omersakalli.marvelcomics.data.Repository
import com.omersakalli.marvelcomics.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _isDatabaseEmpty: SingleLiveData<Boolean> = SingleLiveData()
    val isDataBaseEmpty: LiveData<Boolean>
        get() = _isDatabaseEmpty

    fun checkDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            _isDatabaseEmpty.postValue(repository.getComicsFromDatabase().variable.isNullOrEmpty())
        }
    }
}