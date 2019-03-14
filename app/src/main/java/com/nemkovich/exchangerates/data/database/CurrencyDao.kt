package com.nemkovich.exchangerates.data.database

import android.arch.persistence.room.*
import com.nemkovich.exchangerates.ui.fragment.currencyList.data.CurrencyUiEntity
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currencies WHERE id = :id")
    fun getCurrencyById(id: Int): CurrencyUiEntity?

    @Query("SELECT * FROM currencies WHERE favorite = :favorite ORDER BY position ASC")
    fun getCurrencyFavorites(favorite: Int): Single<List<CurrencyUiEntity>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currency: CurrencyUiEntity)

    @Update
    fun updateCurrencies(list: List<CurrencyUiEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currency: List<CurrencyUiEntity>)

    @Query("SELECT * FROM currencies ORDER BY position ASC")
    fun getAll(): Single<List<CurrencyUiEntity>>
}