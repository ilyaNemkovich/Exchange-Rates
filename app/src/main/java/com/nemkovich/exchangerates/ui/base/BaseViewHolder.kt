package com.nemkovich.exchangerates.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity

abstract class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(data: CurrencyUiEntity)
}