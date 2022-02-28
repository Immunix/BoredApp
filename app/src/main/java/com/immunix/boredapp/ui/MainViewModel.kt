package com.immunix.boredapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immunix.boredapp.repo.MainRepo
import com.immunix.boredapp.state.ViewState
import com.immunix.boredapp.util.HasInternetAccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepo
) : ViewModel() {

    private val _boredActivity: MutableLiveData<ViewState> = MutableLiveData()
    val boredActivity: LiveData<ViewState> get() = _boredActivity

    init {
        fetchData()
    }

    fun fetchData() = viewModelScope.launch {
        val hasInternet = withContext(Dispatchers.IO) {
            HasInternetAccess.execute()
        }

        if (hasInternet) {
            _boredActivity.value = ViewState.Loading
            try {
                val response = repo.getBoredActivity()
                _boredActivity.value = ViewState.Success(response)
            } catch (e: IOException) {
                _boredActivity.value = ViewState.Failure("Couldn't fetch data. ${e.message}")
            }
        } else {
            _boredActivity.value = ViewState.Failure("No internet connection.")
        }
    }
}