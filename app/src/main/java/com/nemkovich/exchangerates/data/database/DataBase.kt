package com.nemkovich.exchangerates.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity

@Database(entities = [CurrencyUiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}