package com.retailinmotion.philip.arnold.repo

import com.retailinmotion.philip.arnold.model.Forecast
import com.retailinmotion.philip.arnold.network.ApiNetwork
import com.retailinmotion.philip.arnold.network.UseCaseResult

class ForecastRepositoryImpl(val api: ApiNetwork): ForecastRepository {
    override suspend fun getForecast(stop: String): UseCaseResult<Forecast> {
        return try {
            val result = api.getForecast(stop = stop).await()
            UseCaseResult.Success(result)
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}