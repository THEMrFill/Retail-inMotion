package com.retailinmotion.philip.arnold.repo

import com.retailinmotion.philip.arnold.consts.Constants
import com.retailinmotion.philip.arnold.model.Direction
import com.retailinmotion.philip.arnold.model.Forecast
import com.retailinmotion.philip.arnold.model.Tram
import com.retailinmotion.philip.arnold.network.UseCaseResult

class ForecastRepositoryImplMorningMock: ForecastRepository {
    override suspend fun getForecast(stop: String): UseCaseResult<Forecast> {
        val trams1 = listOf(
                Tram(
                        duemins = "DUE",
                        destination = "stop 1",
                ),
                Tram(
                        duemins = "5",
                        destination = "stop 2",
                ),
                Tram(
                        duemins = "7",
                        destination = "stop 3",
                ),
        )
        val trams2 = listOf(
                Tram(
                        duemins = "3",
                        destination = "stop 4",
                ),
                Tram(
                        duemins = "8",
                        destination = "stop 5",
                ),
        )
        val direction1 = Direction(
                name = Constants.OUTBOUND,
                trams = trams1,
        )
        val direction2 = Direction(
                name = Constants.INBOUND,
                trams = trams2,
        )

        val result = Forecast(
                message = "message",
                created = "2021-01-08T11:00:00",
                stop = Constants.MORNING_STATION,
                stopAbv = Constants.MORNING_STATION,
                directions = listOf(direction1, direction2),
        )
        return UseCaseResult.Success(result)
    }
}

class ForecastRepositoryImplAfternoonMock: ForecastRepository {
    override suspend fun getForecast(stop: String): UseCaseResult<Forecast> {
        val trams1 = listOf(
            Tram(
                duemins = "DUE",
                destination = "stop A",
            ),
            Tram(
                duemins = "5",
                destination = "stop B",
            ),
            Tram(
                duemins = "7",
                destination = "stop C",
            ),
        )
        val trams2 = listOf(
            Tram(
                duemins = "3",
                destination = "stop D",
            ),
            Tram(
                duemins = "8",
                destination = "stop E",
            ),
        )
        val direction1 = Direction(
            name = Constants.OUTBOUND,
            trams = trams1,
        )
        val direction2 = Direction(
            name = Constants.INBOUND,
            trams = trams2,
        )

        val result = Forecast(
            message = "message",
            created = "2021-01-08T16:00:00",
            stop = Constants.AFTERNOON_STATION,
            stopAbv = Constants.AFTERNOON_STATION,
            directions = listOf(direction1, direction2),
        )
        return UseCaseResult.Success(result)
    }
}