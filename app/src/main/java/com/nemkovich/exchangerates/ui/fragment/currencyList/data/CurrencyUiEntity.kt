package com.nemkovich.exchangerates.ui.fragment.currencyList.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.nemkovich.exchangerates.data.network.dto.CurrencyResponse

@Entity(tableName = "currencies")
class CurrencyUiEntity(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "abbreviation")
    val abbreviation: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "first_rate")
    val firstRate: Double,

    @ColumnInfo(name = "second_rate")
    val secondRate: Double,

    @ColumnInfo(name = "scale")
    val scale: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Int = 0,

    @ColumnInfo(name = "position")
    var position: Int = 0
) {
    companion object {
        fun create(pair: Pair<CurrencyResponse, CurrencyResponse>) =
            CurrencyUiEntity(
                pair.first.Cur_ID,
                pair.first.Cur_Abbreviation,
                pair.first.Cur_Name,
                pair.first.Cur_OfficialRate,
                pair.second.Cur_OfficialRate,
                pair.first.Cur_Scale
            )
    }
}
