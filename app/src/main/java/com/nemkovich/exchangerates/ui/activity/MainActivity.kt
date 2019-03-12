package com.nemkovich.exchangerates.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.nemkovich.exchangerates.R
import com.nemkovich.exchangerates.databinding.ActivityMainBinding
import com.nemkovich.exchangerates.ui.base.BaseActivity
import com.nemkovich.exchangerates.ui.fragment.currencyList.CurrencyListFragment
import com.nemkovich.exchangerates.ui.utils.FragmentStack

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(), FragmentStack {

    override val viewModel: MainActivityViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, CurrencyListFragment.newInstance())
                .commit()
        }
    }

    override fun push(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun pop() {
        supportFragmentManager.popBackStack()
    }

}
