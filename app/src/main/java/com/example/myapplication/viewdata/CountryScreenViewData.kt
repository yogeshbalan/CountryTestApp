package com.example.myapplication.viewdata

import com.example.myapplication.model.CountryItem
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class CountryScreenViewData {

    private val countryListObservable = BehaviorSubject.create<List<CountryItem>>()

    fun observeCountryList(): Observable<List<CountryItem>> = countryListObservable

    fun setCountryList(list: List<CountryItem>) = countryListObservable.onNext(list)
}