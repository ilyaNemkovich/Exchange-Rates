package com.nemkovich.exchangerates.data.repository

import com.nemkovich.exchangerates.data.database.CurrencyDao
import com.nemkovich.exchangerates.data.network.NbrbApi
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import com.nemkovich.exchangerates.dto.CurrencyResponse
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import com.nemkovich.exchangerates.ui.utils.CalendarHelper
import com.nemkovich.exchangerates.ui.utils.toApiFormat
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
                                    toCurrencyList(Pair(todayList, tomorrowList))

                                } else {
                                    CalendarHelper.firstDate = CalendarHelper.yesterday
                                    CalendarHelper.secondDate = CalendarHelper.today
                                    toCurrencyList(Pair(yesterdayList, todayList))
                                }
                            }
                    }
            }

    fun getCurrencyFromDb(): Single<List<CurrencyUiEntity>> {
        return currencyDao.getAll()
    }

    fun updateBd(list: List<CurrencyUiEntity>) {
        currencyDao.updateCurrencies(list)
    }

    private fun toCurrencyList(pair: Pair<List<CurrencyResponse>, List<CurrencyResponse>>): Single<List<CurrencyUiEntity>>? {
        pair.first.map { currencyEntity ->
            CurrencyUiEntity.create(Pair(currencyEntity, pair.second[pair.first.indexOf(currencyEntity)]))
                .also { currency ->
                    val currencyDB = currencyDao.getCurrencyById(currency.id)
                    currency.favorite = currencyDB?.favorite ?: 1
                    currency.position = currencyDB?.position ?: pair.first.indexOf(currencyEntity)
                    currencyDao.insertCurrency(currency)
                }
        }
        return currencyDao.getCurrencyFavorites(1)
    }
}
