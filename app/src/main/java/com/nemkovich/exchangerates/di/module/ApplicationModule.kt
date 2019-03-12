package com.nemkovich.exchangerates.di.module

import android.content.Context
import com.nemkovich.exchangerates.App
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {
    @ApplicationScope
    @Binds
    abstract fun bindApplicationContext(application: App): Context
}