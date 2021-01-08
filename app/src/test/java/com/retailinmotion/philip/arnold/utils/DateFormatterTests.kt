package com.retailinmotion.philip.arnold.utils

import org.junit.Test
import junit.framework.TestCase.assertEquals
import com.retailinmotion.philip.arnold.utils.formatDate

class DateFormatterTests {
    @Test
    fun `check simple date time format`() {
        val date = "2021-08-01T11:50:00"
        assertEquals("01/08/2021", date.formatDate())
    }

    @Test
    fun `check simple date format`() {
        val date = "2021-08-01"
        assertEquals("01/08/2021", date.formatDate())
    }

    @Test
    fun `check broken date`() {
        val date = "2021"
        assertEquals("2021", date)
    }

}