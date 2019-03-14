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
    val mutableIsError = MutableLiveData<Boolean>()
    var isLoaded: Boolean = false

    init {
        mutableIsError.postValue(false)
    }

    fun loadCurrencyList() {
        repository.getCurrencyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                mutableCurrencyList.postValue(response)
                isLoaded = true
            }, {
                mutableIsError.postValue(true)
            }).let(compositeDisposable::add)
    }

    fun refreshList() {
        repository.getFavorites()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { response ->
                mutableCurrencyList.postValue(response)
                isLoaded = true
            }?.let(compositeDisposable::add)
    }
}