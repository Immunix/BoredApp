package com.immunix.boredapp.state

import com.immunix.boredapp.data.model.BoredModel

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: BoredModel) : ViewState()
    data class Failure(val msg: String) : ViewState()
}
