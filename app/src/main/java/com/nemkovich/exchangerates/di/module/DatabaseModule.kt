package com.nemkovich.exchangerates.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.nemkovich.exchangerates.data.database.AppDatabase
import com.nemkovich.exchangerates.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @ApplicationScope
    @Provides
    fun provideAppDatabase(context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "database")
        .build()

    @ApplicationScope
    @Provides
    fun provideScheduleDao(appDatabase: AppDatabase) = appDatabase.currencyDao()
}