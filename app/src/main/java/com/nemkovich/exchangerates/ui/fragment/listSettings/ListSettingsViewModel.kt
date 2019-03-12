package com.nemkovich.exchangerates.ui.fragment.listSettings

import com.nemkovich.exchangerates.data.repository.CurrencyRepository
import com.nemkovich.exchangerates.ui.base.BaseViewModel
import javax.inject.Inject

class ListSettingsViewModel @Inject constructor(private val repository: CurrencyRepository) : BaseViewModel()