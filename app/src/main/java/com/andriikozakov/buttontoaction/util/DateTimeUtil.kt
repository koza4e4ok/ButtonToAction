package com.andriikozakov.buttontoaction.util

import com.andriikozakov.buttontoaction.util.Constants.Companion.DATE_FORMAT
import com.andriikozakov.buttontoaction.util.Constants.Companion.TIME_ZONE
import java.text.SimpleDateFormat
import java.util.*

fun Date.toDate(
    dateFormat: String = DATE_FORMAT,
    timeZone: TimeZone = TimeZone.getTimeZone(TIME_ZONE)
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    val d = parser.format(this)
    return parser.parse(d)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun getYear(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.YEAR)
}

fun getMonth(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.MONTH) + 1
}

fun getDay(date: Date): Int {
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.DATE)
}

fun isSameDay(date1: Date?, date2: Date?): Boolean {
    require(!(date1 == null || date2 == null)) { "Dates must not be null" }
    val cal1 = Calendar.getInstance()
    cal1.time = date1
    val cal2 = Calendar.getInstance()
    cal2.time = date2
    return isSameDay(cal1, cal2)
}

private fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
    require(!(cal1 == null || cal2 == null)) { "Dates must not be null" }
    return cal1[Calendar.ERA] == cal2[Calendar.ERA] && cal1[Calendar.YEAR] == cal2[Calendar.YEAR] && cal1[Calendar.DAY_OF_YEAR] == cal2[Calendar.DAY_OF_YEAR]
}

fun isToday(date: Date?): Boolean {
    return isSameDay(date, Calendar.getInstance().time)
}

fun getDayOfWeek() = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE)).get(Calendar.DAY_OF_WEEK)

fun getTimeInMillis() = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE)).timeInMillis
