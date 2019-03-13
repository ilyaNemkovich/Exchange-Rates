package com.nemkovich.exchangerates.di.component

import com.nemkovich.exchangerates.App
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import com.nemkovich.exchangerates.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
        RxModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}