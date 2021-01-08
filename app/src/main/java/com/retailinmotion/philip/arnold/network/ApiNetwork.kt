package com.retailinmotion.philip.arnold.network

import com.retailinmotion.philip.arnold.model.Forecast
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNetwork {
    @GET("get.ashx")
    fun getForecast(
        @Query("action") action: String = "forecast",
        @Query("stop") stop: String,
        @Query("encrypt") encrypt: String = "false",
    ): Deferred<Forecast>
}