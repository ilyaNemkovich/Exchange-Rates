package com.nemkovich.exchangerates.data.network

import com.nemkovich.exchangerates.dto.CurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NbrbApi {
    @GET("API/ExRates/Rates")
    fun getCurrencyList(@Query("onDate") onDate: String,
                        @Query("Periodicity") periodicity: Int = 0): Single<List<CurrencyResponse>>
}