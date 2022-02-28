package com.immunix.boredapp.data.api

import com.immunix.boredapp.data.model.BoredModel
import retrofit2.http.GET

interface BoredAPI {

    @GET("/api/activity/")
    suspend fun getBoredActivity(): BoredModel

}