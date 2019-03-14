package com.nemkovich.exchangerates.ui.utils

import android.arch.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

object CalendarHelper {

    val mutableFirstDay = MutableLiveData<String>()

    val mutableSecondDay = MutableLiveData<String>()

    var firstDate: Date? = null

    var secondDate: Date? = null

    val today: Date = Calendar.getInstance().time

    val tomorrow: Date = Calendar.getInstance()
        .apply {
            add(Calendar.DATE, 1)
        }.time

    val yesterday: Date = Calendar.getInstance()
        .apply {
            add(Calendar.DATE, -1)
        }.time

}

object DefaultCurrencyHelper {
    val listOfDefaultCurrency = listOf(
        "USD",
        "EUR",
        "RUB"
    )
}

fun Date.toApiFormat(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this)

fun Date.toUIFormat(): String = SimpleDateFormat("dd.MM.yy", Locale.US).format(this)
