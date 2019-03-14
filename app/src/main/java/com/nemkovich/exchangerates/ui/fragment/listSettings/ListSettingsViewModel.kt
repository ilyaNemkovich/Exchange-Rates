package com.nemkovich.exchangerates.ui.fragment.listSettings

import android.arch.lifecycle.MutableLiveData
import com.nemkovich.exchangerates.data.repository.CurrencyRepository
import com.nemkovich.exchangerates.ui.base.BaseViewModel
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListSettingsViewModel @Inject constructor(private val repository: CurrencyRepository) : BaseViewModel() {
    val mutableCurrencyList = MutableLiveData<List<CurrencyUiEntity>>()

    init {
        loadCurrencyList()
    }

    fun loadCurrencyList() {
        repository.getCurrencyFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                mutableCurrencyList.postValue(response)
            }.let(compositeDisposable::add)
    }

    fun updateData(list: List<CurrencyUiEntity>) {
        Completable.fromAction {
            list.forEach { it.position = list.indexOf(it) }
            repository.updateBd(list)
            mutableCurrencyList.postValue(list)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let(compositeDisposable::add)
    }
}