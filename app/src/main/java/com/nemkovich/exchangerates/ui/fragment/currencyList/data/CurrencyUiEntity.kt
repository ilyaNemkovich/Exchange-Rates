package com.nemkovich.exchangerates.ui.fragment.currencyList.data

import com.nemkovich.exchangerates.dto.CurrencyResponse

class CurrencyUiEntity(
    val id: Int,
    val abbreviation: String,
    val name: String,
    val firstRate: Double,
    val secondRate: Double,
    val scale: Int,
    var favorite: Boolean = true,
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
