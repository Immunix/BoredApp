package com.immunix.boredapp.repo

import com.immunix.boredapp.data.api.BoredAPI
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val boredAPI: BoredAPI
) {
    suspend fun getBoredActivity() = boredAPI.getBoredActivity()
}