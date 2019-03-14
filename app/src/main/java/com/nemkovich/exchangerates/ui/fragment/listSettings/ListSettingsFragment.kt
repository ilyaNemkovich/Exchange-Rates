package com.nemkovich.exchangerates.ui.fragment.listSettings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.nemkovich.exchangerates.R
import com.nemkovich.exchangerates.databinding.FragmentListSettingsBinding
import com.nemkovich.exchangerates.ui.activity.MainActivity
import com.nemkovich.exchangerates.ui.base.BaseFragment
import com.nemkovich.exchangerates.ui.fragment.currencyList.adapter.CurrencyListAdapter
import com.nemkovich.exchangerates.ui.utils.FragmentStack
import javax.inject.Inject

class ListSettingsFragment : BaseFragment<FragmentListSettingsBinding, ListSettingsViewModel>() {

    @Inject
    lateinit var recyclerAdapter: CurrencyListAdapter

    @Inject
    lateinit var itemTouchHelper: ItemTouchHelper

    private val fragmentStack: FragmentStack by lazy { activity as MainActivity }

    override val viewModel: ListSettingsViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(ListSettingsViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.fragment_list_settings

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.mutableCurrencyList.observe(this, Observer {
            if (it != null) {
                recyclerAdapter.setItems(it)
            }
        })
    }

    private fun setupView() {
        recyclerAdapter.changeViewType(1)
        itemTouchHelper.attachToRecyclerView(viewDataBinding.recyclerView)
        viewDataBinding.recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@ListSettingsFragment.context)
                .apply { recycleChildrenOnDetach = true }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.confirm -> {
                viewModel.updateData(recyclerAdapter.currencyList)
                fragmentStack.pop()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = ListSettingsFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}