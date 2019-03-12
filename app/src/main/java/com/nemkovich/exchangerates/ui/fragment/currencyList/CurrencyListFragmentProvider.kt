package com.nemkovich.exchangerates.ui.fragment.currencyList

import com.nemkovich.exchangerates.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CurrencyListFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CurrencyListModule::class])
    abstract fun bindFragmetn(): CurrencyListFragment
}