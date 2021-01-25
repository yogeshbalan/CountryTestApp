package com.example.myapplication.presenter

import com.example.myapplication.model.CountryItem
import com.example.myapplication.viewdata.CountryScreenViewData

class CountryScreenPresenter(
    val viewData: CountryScreenViewData
) {
    fun setData(list: List<CountryItem>) {
        viewData.setCountryList(list)
    }
}