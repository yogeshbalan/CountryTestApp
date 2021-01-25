package com.example.myapplication.repository.interactor

import com.example.myapplication.model.CountryItem
import io.reactivex.rxjava3.core.Observable

class CountryListInteractor {
    fun loadCountries(): Observable<List<CountryItem>> {
        return Observable.fromCallable {
            getMockCountryList()
        }
    }

    private fun getMockCountryList(): List<CountryItem> {
        val country1 = CountryItem("India", "Delhi")
        val country2 = CountryItem("China", "Beijing")
        val country3 = CountryItem("Pakistan", "Islamabad")
        return mutableListOf(country1, country2, country3)

    }
}