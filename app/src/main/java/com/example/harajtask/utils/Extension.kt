package com.example.harajtask.utils

import android.content.res.AssetManager
import com.example.harajtask.utils.Constants.DayMeasure
import com.example.harajtask.utils.Constants.HourMeasure
import com.example.harajtask.utils.Constants.MinuteMeasure
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText() }


fun Long.getPostWhenTime(currentDate: Date): HashMap<String, Int> {

    val map = hashMapOf<String, Int>()
    val oldDate = this.getDateFromTimeStamp()

    var different: Long = currentDate.time - oldDate.time

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24

    val elapsedDays = different / daysInMilli
    different %= daysInMilli

    val elapsedHours = different / hoursInMilli
    different %= hoursInMilli

    val elapsedMinutes = different / minutesInMilli
    different %= minutesInMilli

    if (elapsedDays > 0) {
        map[DayMeasure] = elapsedDays.toInt()
    } else if (elapsedDays == 0L && elapsedHours > 0) {
        map[HourMeasure] = elapsedHours.toInt()
    } else if (elapsedDays == 0L && elapsedHours == 0L) {
        map[MinuteMeasure] = elapsedMinutes.toInt()

    }

    return map
}

fun Long.getDateFromTimeStamp(): Date {

    val calendar = Calendar.getInstance()
    val tz = TimeZone.getDefault()
    calendar.timeInMillis = this * 1000
    calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
    return calendar.time as Date

}

fun Long.getDateString(): String {
    val date = this.getDateFromTimeStamp()
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm aaa", Locale.forLanguageTag("ar"))
    return sdf.format(date.time)
}