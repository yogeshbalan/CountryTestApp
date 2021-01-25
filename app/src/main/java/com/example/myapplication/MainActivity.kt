package com.example.myapplication

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CountryListAdapter
import com.example.myapplication.controller.CountryScreenController
import com.example.myapplication.presenter.CountryScreenPresenter
import com.example.myapplication.repository.CountryListRepository
import com.example.myapplication.repository.interactor.CountryListInteractor
import com.example.myapplication.viewdata.CountryScreenViewData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var controller: CountryScreenController
    private val disposable = CompositeDisposable()
    private lateinit var countryAdapter: CountryListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        initController()
        controller.onStart()
        initViews()
        observeData()
    }

    private fun initViews() {
        supportActionBar?.title = "Select Country"
        recyclerView = findViewById(R.id.recyclerView)
        countryAdapter = CountryListAdapter { selectedCountry ->
            findViewById<Toolbar>(R.id.toolbar).title = selectedCountry.countryCapital
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = countryAdapter
        }
    }

    private fun observeData() {
        disposable.add(controller.viewData.observeCountryList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { countryList ->
            countryAdapter.countries = countryList
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