package com.hpcoding.newsapp_compose.utils

import java.text.SimpleDateFormat
import java.util.*

const val PATTERN_SERVER_UTC_FULL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"


fun String.asDateTimeString(): String {
    val formatter = SimpleDateFormat("dd/MM/yy - HH:mm:ss", Locale.getDefault())
    return formatter.format(this)
}


fun String.toLocalDate(
    inputPattern: String = PATTERN_SERVER_UTC_FULL_DATE_FORMAT,
    outputPattern: String = "dd/MM/yyyy - HH:mm:ss"
): String {
    return try {
        val sdfInput = SimpleDateFormat(inputPattern)
        sdfInput.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdfInput.parse(this)
        val sdfOutput = SimpleDateFormat(outputPattern, Locale.getDefault())
        sdfOutput.timeZone = TimeZone.getDefault()
        val formatted = sdfOutput.format(date)
        formatted
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }

}