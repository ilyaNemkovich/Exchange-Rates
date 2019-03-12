package com.nemkovich.exchangerates.di.module

import com.nemkovich.exchangerates.di.annotations.ActivityScope
import com.nemkovich.exchangerates.ui.activity.MainActivity
import com.nemkovich.exchangerates.ui.fragment.currencyList.CurrencyListFragmentProvider
import com.nemkovich.exchangerates.ui.fragment.listSettings.ListSettingsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        CurrencyListFragmentProvider::class,
        ListSettingsFragmentProvider::class
    ])
    abstract fun bindMainActivity(): MainActivity
}