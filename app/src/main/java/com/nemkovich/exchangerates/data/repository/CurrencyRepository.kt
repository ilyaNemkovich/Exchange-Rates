package com.nemkovich.exchangerates.data.repository

import com.nemkovich.exchangerates.data.database.CurrencyDao
import com.nemkovich.exchangerates.data.network.NbrbApi
import com.nemkovich.exchangerates.data.network.dto.CurrencyResponse
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import com.nemkovich.exchangerates.ui.utils.CalendarHelper
import com.nemkovich.exchangerates.ui.utils.DefaultCurrencyHelper
import com.nemkovich.exchangerates.ui.utils.toApiFormat
import com.nemkovich.exchangerates.ui.utils.toUIFormat
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScope
class CurrencyRepository @Inject constructor(private val api: NbrbApi, private val currencyDao: CurrencyDao) {

    fun getCurrencyList(): Single<List<CurrencyUiEntity>> =
        api.getCurrencyList(CalendarHelper.today.toApiFormat())
            .flatMap { todayList ->
                api.getCurrencyList(CalendarHelper.tomorrow.toApiFormat())
                    .flatMap { tomorrowList ->
                        api.getCurrencyList(CalendarHelper.yesterday.toApiFormat())
                            .flatMap { yesterdayList ->
                                if (tomorrowList.isNotEmpty()) {
                                    CalendarHelper.firstDate = CalendarHelper.today
                                    CalendarHelper.secondDate = CalendarHelper.tomorrow
                                    CalendarHelper.mutableFirstDay.postValue(CalendarHelper.today.toUIFormat())
                                    CalendarHelper.mutableSecondDay.postValue(CalendarHelper.tomorrow.toUIFormat())
                                    toCurrencyList(Pair(todayList, tomorrowList))
                                    getFavorites()
                                } else {
                                    CalendarHelper.firstDate = CalendarHelper.yesterday
                                    CalendarHelper.secondDate = CalendarHelper.today
                                    CalendarHelper.mutableFirstDay.postValue(CalendarHelper.yesterday.toUIFormat())
                                    CalendarHelper.mutableSecondDay.postValue(CalendarHelper.today.toUIFormat())
                                    toCurrencyList(Pair(yesterdayList, todayList))
                                    getFavorites()
                                }
                            }
                    }
            }

    fun getFavorites(): Single<List<CurrencyUiEntity>>? = currencyDao.getCurrencyFavorites(1)

    fun getCurrencyFromDb(): Single<List<CurrencyUiEntity>> = currencyDao.getAll()

    fun updateBd(list: List<CurrencyUiEntity>) = currencyDao.updateCurrencies(list)

    private fun toCurrencyList(pair: Pair<List<CurrencyResponse>, List<CurrencyResponse>>) {
        pair.first.map { currencyEntity ->
            CurrencyUiEntity.create(Pair(currencyEntity, pair.second[pair.first.indexOf(currencyEntity)]))
                .also { currency ->
                    val currencyDB = currencyDao.getCurrencyById(currency.id)
                    currency.favorite = currencyDB?.favorite ?: setDefaultFavorites(currency)
                    currency.position = currencyDB?.position ?: pair.first.indexOf(currencyEntity)
                    currencyDao.insertCurrency(currency)
                }
        }
    }

    private fun setDefaultFavorites(currency: CurrencyUiEntity): Int {
        DefaultCurrencyHelper.listOfDefaultCurrency.forEach { if (it == currency.abbreviation) return 1 }
        return 0
    }
}
