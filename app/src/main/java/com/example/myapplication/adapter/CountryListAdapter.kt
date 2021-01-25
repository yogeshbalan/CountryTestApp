package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CountryItem

class CountryListAdapter(val context: Context) : RecyclerView.Adapter<CountryItemViewHolder>() {

    val countries = mutableListOf<CountryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_country, parent, false))
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.apply {

        }
    }

    override fun getItemCount() = countries.size
}