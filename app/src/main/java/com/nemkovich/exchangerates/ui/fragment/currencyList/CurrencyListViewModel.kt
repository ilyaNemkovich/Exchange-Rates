package com.nemkovich.exchangerates.ui.fragment.currencyList

import android.arch.lifecycle.MutableLiveData
import com.nemkovich.exchangerates.data.repository.CurrencyRepository
import com.nemkovich.exchangerates.ui.base.BaseViewModel
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(private val repository: CurrencyRepository) : BaseViewModel() {
    val mutableCurrencyList = MutableLiveData<List<CurrencyUiEntity>>()

    init {
        loadCurrencyList(true)
    }

    fun loadCurrencyList(isNetwork: Boolean) {
        repository.getCurrencyList(isNetwork)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                mutableCurrencyList.postValue(response)
            }.let(compositeDisposable::add)
    }
}