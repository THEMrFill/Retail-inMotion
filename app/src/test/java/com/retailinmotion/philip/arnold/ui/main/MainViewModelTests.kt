package com.retailinmotion.philip.arnold.ui.main

import com.nhaarman.mockitokotlin2.mock
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.util.*

class MainViewModelTests {
    val viewModel = MainViewModel(mock())

    @Test
    fun `test is morning at midnight`() {
        val cal = GregorianCalendar().apply {
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 10)
            set(Calendar.DAY_OF_MONTH, 20)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        assertTrue(viewModel.isItMorning(cal.time))
    }

    @Test
    fun `test is morning at mid morning`() {
        val cal = GregorianCalendar().apply {
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 10)
            set(Calendar.DAY_OF_MONTH, 20)
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 15)
            set(Calendar.SECOND, 0)
        }
        assertTrue(viewModel.isItMorning(cal.time))
    }

    @Test
    fun `test is morning at midday`() {
        val cal = GregorianCalendar().apply {
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 10)
            set(Calendar.DAY_OF_MONTH, 20)
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        assertTrue(viewModel.isItMorning(cal.time))
    }

    @Test
    fun `test is morning at just after midday`() {
        val cal = GregorianCalendar().apply {
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 10)
            set(Calendar.DAY_OF_MONTH, 20)
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 5)
            set(Calendar.SECOND, 0)
        }
        assertFalse(viewModel.isItMorning(cal.time))
    }

    @Test
    fun `test is morning at mid afternoon`() {
        val cal = GregorianCalendar().apply {
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 10)
            set(Calendar.DAY_OF_MONTH, 20)
            set(Calendar.HOUR_OF_DAY, 15)
            set(Calendar.MINUTE, 30)
            set(Calendar.SECOND, 0)
        }
        assertFalse(viewModel.isItMorning(cal.time))
    }

    @Test
    fun `test is morning at evening`() {
        val cal = GregorianCalendar().apply {
            set(Calendar.YEAR, 2020)
            set(Calendar.MONTH, 10)
            set(Calendar.DAY_OF_MONTH, 20)
            set(Calendar.HOUR_OF_DAY, 19)
            set(Calendar.MINUTE, 40)
            set(Calendar.SECOND, 0)
        }
        assertFalse(viewModel.isItMorning(cal.time))
    }
}