package com.retailinmotion.philip.arnold.repo

import com.retailinmotion.philip.arnold.model.Forecast
import com.retailinmotion.philip.arnold.network.UseCaseResult

interface ForecastRepository {
    suspend fun getForecast(stop: String): UseCaseResult<Forecast>

}