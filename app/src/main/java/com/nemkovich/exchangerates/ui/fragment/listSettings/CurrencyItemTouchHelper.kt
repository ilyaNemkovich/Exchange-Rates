package com.nemkovich.exchangerates.ui.fragment.listSettings

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.nemkovich.exchangerates.ui.fragment.currencyList.adapter.CurrencyListAdapter
import javax.inject.Inject

class CurrencyItemTouchHelper
@Inject constructor(private val adapter: CurrencyListAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
        makeMovementFlags(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), 0)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}