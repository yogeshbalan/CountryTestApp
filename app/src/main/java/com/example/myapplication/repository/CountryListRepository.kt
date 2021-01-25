package com.example.myapplication.repository

import com.example.myapplication.model.CountryItem
import com.example.myapplication.repository.interactor.CountryListInteractor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class CountryListRepository(private val interactor: CountryListInteractor) {

    fun loadCountries(): Observable<List<CountryItem>> {
        return interactor.loadCountries().subscribeOn(Schedulers.io())
    }

}