package com.nemkovich.exchangerates.ui.utils

import android.support.v4.app.Fragment

interface FragmentStack {
    fun push(fragment: Fragment)
    fun pop()
}