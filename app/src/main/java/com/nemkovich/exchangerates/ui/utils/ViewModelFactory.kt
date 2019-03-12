package com.nemkovich.exchangerates.ui.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@ApplicationScope
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        requireNotNull(viewModels[modelClass]?.get()) as T
}