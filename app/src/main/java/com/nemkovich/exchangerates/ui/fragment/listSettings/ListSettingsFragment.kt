package com.nemkovich.exchangerates.ui.fragment.listSettings

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.nemkovich.exchangerates.R
import com.nemkovich.exchangerates.databinding.FragmentListSettingsBinding
import com.nemkovich.exchangerates.ui.activity.MainActivity
import com.nemkovich.exchangerates.ui.base.BaseFragment
import com.nemkovich.exchangerates.ui.utils.FragmentStack

class ListSettingsFragment : BaseFragment<FragmentListSettingsBinding, ListSettingsViewModel>(){

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

    private fun subscribeToLiveData(){

    }

    private fun setupView(){

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.settings_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.confirm -> fragmentStack.pop()
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