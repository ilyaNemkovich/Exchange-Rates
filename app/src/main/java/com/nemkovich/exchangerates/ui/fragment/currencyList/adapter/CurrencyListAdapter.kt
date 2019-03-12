package com.nemkovich.exchangerates.ui.fragment.currencyList.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import com.nemkovich.exchangerates.databinding.ItemCurrencyBinding

class CurrencyListAdapter(): RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder>(){

        private val _quizList = ArrayList<CurrencyUiEntity>()

        class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(currencyUiEntity: CurrencyUiEntity) {
                binding.tvAbbreviation.text = currencyUiEntity.abbreviation
                binding.tvFirstRate.text = currencyUiEntity.firstRate.toString()
                binding.tvSecondRate.text = currencyUiEntity.secondRate.toString()
                binding.tvName.text = currencyUiEntity.name
            }
        }

        override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) = holder.bind(_quizList[position])

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
            val viewBinding = ItemCurrencyBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CurrencyViewHolder(viewBinding)
        }

        fun setItems(data: List<CurrencyUiEntity>) {
            _quizList.clear()
            _quizList.addAll(data)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = _quizList.size

        interface OnItemClickListener {
            fun onItemClick(view: View, randomImageQuiz: CurrencyUiEntity)
        }
}