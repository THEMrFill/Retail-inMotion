package com.retailinmotion.philip.arnold.ui.main

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.retailinmotion.philip.arnold.consts.Constants.AFTERNOON_STATION
import com.retailinmotion.philip.arnold.consts.Constants.MORNING_STATION
import com.retailinmotion.philip.arnold.consts.Constants.OUTBOUND
import com.retailinmotion.philip.arnold.consts.Constants.INBOUND
import com.retailinmotion.philip.arnold.model.Direction
import com.retailinmotion.philip.arnold.model.Forecast
import com.retailinmotion.philip.arnold.network.SingleLiveEvent
import com.retailinmotion.philip.arnold.network.UseCaseResult
import com.retailinmotion.philip.arnold.repo.ForecastRepository
import com.retailinmotion.philip.arnold.repo.TimeRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val repo: ForecastRepository, private val timeRepo: TimeRepository) : ViewModel(), CoroutineScope {
    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData(true)
    val showError = SingleLiveEvent<String>()
    val forecast = MutableLiveData<Forecast>()
    val trams = MutableLiveData<Direction>()

    var stop: String = MORNING_STATION
    var direction: String = OUTBOUND
    init {
        loadForecast()
    }

    private fun loadForecast() {
        when (isItMorning(timeRepo.getTime())) {
            true -> {
                stop = MORNING_STATION
                direction = OUTBOUND
            }
            false -> {
                stop = AFTERNOON_STATION
                direction = INBOUND
            }
        }

        showLoading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { repo.getForecast(stop) }
            showLoading.value = false
            when (result) {
                is UseCaseResult.Success -> {
                    forecast.value = result.data
                    trams.value = getDirection(result.data)
                }
                is UseCaseResult.Error -> {
                    showError.value = result.exception.message
                    Log.e("MainViewModel", "error: ${result.exception.message}", result.exception)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getDirection(forecast: Forecast): Direction {
        return forecast.directions.first {
            it.name == direction
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isItMorning(date: Date): Boolean {
        val cal = GregorianCalendar().apply {
            time = date
        }
        return when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> true
            12 -> cal.get(Calendar.MINUTE) == 0
            else -> false
        }
    }
}