package com.nemkovich.exchangerates.ui.fragment.currencyList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.nemkovich.exchangerates.R
import com.nemkovich.exchangerates.databinding.FragmentCurrencyListBinding
import com.nemkovich.exchangerates.ui.activity.MainActivity
import com.nemkovich.exchangerates.ui.base.BaseFragment
import com.nemkovich.exchangerates.ui.fragment.currencyList.adapter.CurrencyListAdapter
import com.nemkovich.exchangerates.ui.fragment.listSettings.ListSettingsFragment
import com.nemkovich.exchangerates.ui.utils.CalendarHelper
import com.nemkovich.exchangerates.ui.utils.FragmentStack
import javax.inject.Inject

class CurrencyListFragment : BaseFragment<FragmentCurrencyListBinding, CurrencyListViewModel>() {

    @Inject
    lateinit var recyclerAdapter: CurrencyListAdapter

    private val fragmentStack: FragmentStack by lazy { activity as MainActivity }

    override val viewModel: CurrencyListViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(CurrencyListViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.fragment_currency_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        subscribeToLiveData()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isLoaded) {
            viewModel.refreshList()
        } else {
            viewModel.loadCurrencyList()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.mutableCurrencyList.observe(this, Observer {
            if (it != null) {
                recyclerAdapter.setItems(it)
            }
        })

        viewModel.mutableIsError.observe(this, Observer {
            if (it != null) {
                errorViewState(it)
            }
        })

        CalendarHelper.mutableFirstDay.observe(this, Observer {
            if (it != null) {
                viewDataBinding.tvFirstDay.text = it
            }
        })

        CalendarHelper.mutableSecondDay.observe(this, Observer {
            if (it != null) {
                viewDataBinding.tvSecondDay.text = it
            }
        })
    }

    private fun setupView() {
        viewDataBinding.tvFirstDay.text

        recyclerAdapter.changeViewType(0)
        viewDataBinding.recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@CurrencyListFragment.context)
                .apply { recycleChildrenOnDetach = true }
        }
    }

    private fun errorViewState(isError: Boolean) {
        if (isError) {
            recyclerAdapter.clearData()
            setHasOptionsMenu(false)
            viewDataBinding.tvError.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.settings -> fragmentStack.push(ListSettingsFragment.newInstance())
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance() = CurrencyListFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}