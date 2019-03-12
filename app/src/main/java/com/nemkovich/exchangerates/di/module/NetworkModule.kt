package com.nemkovich.exchangerates.di.module

import com.nemkovich.exchangerates.data.network.NbrbApi
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @ApplicationScope
    @Provides
    fun providesNbrbApi(retrofit: Retrofit) =
        retrofit.create(NbrbApi::class.java)

    @ApplicationScope
    @Provides
    fun providesRetrofit() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://nbrb.by")
        .build()
}