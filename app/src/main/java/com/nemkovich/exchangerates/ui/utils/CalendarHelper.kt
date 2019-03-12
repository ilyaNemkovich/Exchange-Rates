package com.nemkovich.exchangerates.ui.utils

import java.text.SimpleDateFormat
import java.util.*

object CalendarHelper {
    val today: Date = Calendar.getInstance().time
    val tomorrow: Date = Calendar.getInstance()
        .apply {
            add(Calendar.DATE, 1)
        }.time
    val yesterday: Date = Calendar.getInstance()
        .apply {
            add(Calendar.DATE, -1)
        }.time

    var firstDate: Date? = null
    var secondDate: Date? = null
}

fun Date.toApiFormat(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this)

fun Date.toUIFormat(): String = SimpleDateFormat("dd.MM.yy", Locale.US).format(this)
