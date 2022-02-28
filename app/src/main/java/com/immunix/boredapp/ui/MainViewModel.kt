package com.immunix.boredapp.ui

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immunix.boredapp.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo
) : ViewModel() {

    private val _boredActivity: MutableLiveData<ViewState> = MutableLiveData()
    val boredActivity: LiveData<ViewState> get() = _boredActivity

    fun fetchData() = viewModelScope.launch {

    }
}