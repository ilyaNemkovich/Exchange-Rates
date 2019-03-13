package com.nemkovich.exchangerates.ui.fragment.listSettings

import com.nemkovich.exchangerates.di.annotations.FragmentScope
import com.nemkovich.exchangerates.ui.fragment.currencyList.adapter.CurrencyListAdapter
import dagger.Module
import dagger.Provides

@Module
class ListSettingsModule {
    @Provides
    @FragmentScope
    fun provideCurrencyAdapter() = CurrencyListAdapter(1)
}