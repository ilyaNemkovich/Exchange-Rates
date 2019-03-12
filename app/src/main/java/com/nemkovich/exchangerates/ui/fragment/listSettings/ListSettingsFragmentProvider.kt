package com.nemkovich.exchangerates.ui.fragment.listSettings

import com.nemkovich.exchangerates.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListSettingsFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragmetn(): ListSettingsFragment
}