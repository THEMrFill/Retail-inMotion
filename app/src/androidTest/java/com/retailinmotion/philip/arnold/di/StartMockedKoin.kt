package com.retailinmotion.philip.arnold.di

import org.koin.core.context.startKoin

object StartMockedKoin {
    fun startMockedKoin(time: Int) {
        startKoin {
            modules(
                when (time) {
                    0 ->
                        appModulesMockMorning
                    1 ->
                        appModulesMockAfternoon
                    else ->
                        appModulesMockMorning
                }
            )
        }
    }
}