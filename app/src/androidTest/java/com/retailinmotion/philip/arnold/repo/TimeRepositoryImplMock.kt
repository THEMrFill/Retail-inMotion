package com.retailinmotion.philip.arnold.repo

import java.util.*

class TimeRepositoryImplMorningMock: TimeRepository {
    override fun getTime() = makeCalendar(11)
}
class TimeRepositoryImplAfternoonMock: TimeRepository {
    override fun getTime() = makeCalendar(16)
}

fun makeCalendar(hour: Int): Date {
    val calendar = GregorianCalendar()
    calendar.set(Calendar.YEAR, 2021)
    calendar.set(Calendar.MONTH, 0)
    calendar.set(Calendar.DAY_OF_MONTH, 8)
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, 0)
    return calendar.time
}