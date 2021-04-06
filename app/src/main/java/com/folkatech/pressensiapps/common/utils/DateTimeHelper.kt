package com.folkatech.pressensiapps.common.utils

import android.annotation.SuppressLint
import com.folkatech.pressensiapps.common.Common
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
@SuppressLint("ConstantLocale")
object DateTimeHelper {
    val dateFormatDefaultReverse = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateFormatDefault = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val dateFormatShortMonth = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val dateFormatFullMonth = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val dateFormatFullClock = SimpleDateFormat("hh:mm a", Locale.getDefault())


    /**
     * Convert time and date to millis.
     * @param dateTime The string to parse
     * @return time in millis
     */
    @Throws(ParseException::class)
    fun convertToMillis(dateTime: String, dateFormat: String): Long {
        val formatter = SimpleDateFormat(dateFormat, Locale.US)
        return formatter.parse(dateTime).time
    }

    /**
     * Obtain a current time
     * @param dateFormat The date format string
     * @return the current time as a string
     */
    fun getCurrentDateString(dateFormat: String): String {
        val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()

        val currentLocalTime = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00")).time

        return dateFormat.format(currentLocalTime)
    }

    /**
     * Obtain a time
     * @param dateFormat The date format string
     * @param date The date
     * @return the time as a string
     */
    fun getStringFromDate(dateFormat: SimpleDateFormat, date: Date): String {
        return dateFormat.format(date)
    }

    /**
     * Obtain a [Date] from a [String] date
     * @param dateFormat The date format string
     * @param date The date string
     * @return the [Date]
     */
    fun getDateFromString(dateFormat: SimpleDateFormat, date: String): Date =
        dateFormat.parse(date)

    fun convertTime(
        currentDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
        resultDateFormat: SimpleDateFormat = SimpleDateFormat(
            "hh:mm aa dd-MM",
            Locale.getDefault()
        ),
        date: String
    ): String = getStringFromDate(resultDateFormat, getDateFromString(currentDateFormat, date))

    /**
     * Obtain a [Calendar] object from a String.
     * @return the [Calendar] object
     */
    fun getCalendarFromMillis(millis: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar
    }

    /**
     * Obtain a [Calendar] object from a String.
     * @return the [Calendar] object
     */
    fun getCalendarFromDate(dateTime: String, dateFormat: String): Calendar? {
        try {
            val formatter = SimpleDateFormat(dateFormat, Locale.US)
            val date = formatter.parse(dateTime)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar
        } catch (e: ParseException) {
            Common.printStackTrace(e)
            return null
        }
    }

    /**
     * Obtain a difference of days
     * @param startDate The start [Date]
     * @param endDate The end [Date]
     * @return The [Long] from date differences
     */
    fun getDiffDays(startDate: Date, endDate: Date): Long {
        val diffInMillies = endDate.time - startDate.time
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1
    }
}