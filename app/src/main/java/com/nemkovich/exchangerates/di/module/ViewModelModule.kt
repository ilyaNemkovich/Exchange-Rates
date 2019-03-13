package com.nemkovich.exchangerates.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nemkovich.exchangerates.di.annotations.ViewModelKey
import com.nemkovich.exchangerates.ui.activity.MainActivityViewModel
import com.nemkovich.exchangerates.ui.fragment.currencyList.CurrencyListViewModel
import com.nemkovich.exchangerates.ui.fragment.listSettings.ListSettingsViewModel
import com.nemkovich.exchangerates.ui.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModuleFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun mainActivity(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    fun currencyListFragment(viewModel: CurrencyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListSettingsViewModel::class)
    fun listSettingsFragment(viewModel: ListSettingsViewModel): ViewModel
}
