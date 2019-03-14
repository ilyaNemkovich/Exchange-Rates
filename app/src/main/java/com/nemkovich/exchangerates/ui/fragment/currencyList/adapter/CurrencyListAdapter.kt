package com.nemkovich.exchangerates.ui.fragment.currencyList.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nemkovich.exchangerates.databinding.ItemCurrencyBinding
import com.nemkovich.exchangerates.databinding.ItemCurrencySettingBinding
import com.nemkovich.exchangerates.ui.base.BaseViewHolder
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import java.util.*

class CurrencyListAdapter(private var viewType: Int) : RecyclerView.Adapter<BaseViewHolder>() {

    val currencyList = ArrayList<CurrencyUiEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        CURRENCY_LIST -> {
            val viewBinding = ItemCurrencyBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            CurrencyAdapterViewHolders.CurrencyListItemViewHolder(viewBinding)
        }
        SETTINGS_LIST -> {
            val viewBinding = ItemCurrencySettingBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            CurrencyAdapterViewHolders.SettingsListItemViewHolder(viewBinding)
        }
        else -> throw IllegalArgumentException("Illegal view type")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.onBind(currencyList[position])

    override fun getItemCount(): Int = currencyList.size

    override fun getItemViewType(position: Int) = when (viewType) {
        CURRENCY_LIST -> CURRENCY_LIST
        SETTINGS_LIST -> SETTINGS_LIST
        else -> 0
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition)
            for (i in fromPosition until toPosition)
                Collections.swap(currencyList, i, i + 1)
        else
            for (i in fromPosition downTo (toPosition + 1))
                Collections.swap(currencyList, i, i - 1)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun setItems(data: List<CurrencyUiEntity>) {
        currencyList.clear()
        currencyList.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        currencyList.clear()
        notifyDataSetChanged()
    }

    fun changeViewType(type: Int) {
        viewType = type
    }

    companion object ClassesViewTypes {
        const val CURRENCY_LIST = 0
        const val SETTINGS_LIST = 1
    }
}

sealed class CurrencyAdapterViewHolders {

    class CurrencyListItemViewHolder(private val binding: ItemCurrencyBinding) : BaseViewHolder(binding.root) {

        override fun onBind(data: CurrencyUiEntity) {
            binding.tvAbbreviation.text = data.abbreviation
            binding.tvFirstRate.text = data.firstRate.toString()
            binding.tvSecondRate.text = data.secondRate.toString()
            binding.tvName.text = data.scale.toString().plus(" ").plus(data.name)
        }
    }

    class SettingsListItemViewHolder(private val binding: ItemCurrencySettingBinding) : BaseViewHolder(binding.root) {

        override fun onBind(data: CurrencyUiEntity) {
            binding.tvAbbreviation.text = data.abbreviation
            binding.tvName.text = data.scale.toString().plus(" ").plus(data.name)
            binding.swFavorite.isChecked = when (data.favorite) {
                1 -> true
                else -> false
            }
            binding.swFavorite.setOnClickListener {
                data.favorite = when (data.favorite) {
                    0 -> 1
                    1 -> 0
                    else -> 0
                }
            }
        }
    }
}