package com.example.myapplication.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CountryItem

class CountryItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val countryTitle: TextView by lazy { view.findViewById(R.id.countryName) }
    private val countryCapital: TextView by lazy { view.findViewById(R.id.capitolName) }
    private val button: Button by lazy { view.findViewById(R.id.button) }

    fun bind(countryItem: CountryItem, listener: () -> Unit) {
        countryCapital.text = countryItem.countryCapital
        countryTitle.text = countryItem.countryName
        button.setOnClickListener {
            listener.invoke()
        }
    }
}