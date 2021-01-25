package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.controller.CountryScreenController
import com.example.myapplication.presenter.CountryScreenPresenter
import com.example.myapplication.repository.CountryListRepository
import com.example.myapplication.repository.interactor.CountryListInteractor
import com.example.myapplication.viewdata.CountryScreenViewData
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var controller: CountryScreenController
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        initController()
        controller.onStart()
        observeData()
    }

    private fun observeData() {
        disposable.add(controller.viewData.observeCountryList().subscribe { countryList ->
            Log.v("List", countryList.toString())
        })
    }

    private fun initController() {
        val interactor = CountryListInteractor()
        val viewData = CountryScreenViewData()
        val presenter = CountryScreenPresenter(viewData)
        val repo = CountryListRepository(interactor)
        controller = CountryScreenController(presenter, repo)
    }

    override fun onDestroy() {
        super.onDestroy()
        controller.onDestroy()
        disposable.clear()
    }
}