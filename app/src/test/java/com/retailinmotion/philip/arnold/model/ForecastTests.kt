package com.retailinmotion.philip.arnold.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ForecastTests {
    @Test
    fun `check forecast date time formatter`() {
        val forecast = Forecast(
                message = "",
                created = "2021-01-08T11:00:00",
                stop = "stop",
                stopAbv = "abv",
                directions = emptyList(),
        )
        assertEquals("08/01/2021 11:00", forecast.createdLayout())
    }

    @Test
    fun `check forecast date formatter`() {
        val forecast = Forecast(
                message = "",
                created = "2021-01-08",
                stop = "stop",
                stopAbv = "abv",
                directions = emptyList(),
        )
        assertEquals("08/01/2021", forecast.createdLayout())
    }


    @Test
    fun `check forecast non-date formatter`() {
        val forecast = Forecast(
                message = "",
                created = "2021",
                stop = "stop",
                stopAbv = "abv",
                directions = emptyList(),
        )
        assertEquals("2021", forecast.createdLayout())
    }
}