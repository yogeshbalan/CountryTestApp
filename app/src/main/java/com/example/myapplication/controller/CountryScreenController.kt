package com.example.myapplication.controller

import com.example.myapplication.presenter.CountryScreenPresenter
import com.example.myapplication.repository.CountryListRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CountryScreenController(
    private val presenter: CountryScreenPresenter,
    private val repository: CountryListRepository
) {

    private val disposable: CompositeDisposable = CompositeDisposable()

    val viewData = presenter.viewData

    fun onStart() {
        fetchData()
    }

    private fun fetchData() {
        disposable.add(repository.loadCountries().subscribe { countryList ->
            presenter.setData(countryList)
        })
    }

    fun onDestroy() {
        disposable.clear()
    }

}