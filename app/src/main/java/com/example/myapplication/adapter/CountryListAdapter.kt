package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CountryItem

class CountryListAdapter(listener: ((CountryItem) -> Unit)) :
    RecyclerView.Adapter<CountryItemViewHolder>() {

    private var onItemSelectionListener: ((CountryItem) -> Unit)? = null

    init {
        this.onItemSelectionListener = listener
    }

    private val diffCallback = object : DiffUtil.ItemCallback<CountryItem>() {
        override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem.countryName == newItem.countryName
        }

        override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val asyncDiffer = AsyncListDiffer(this, diffCallback)

    var countries: List<CountryItem>
        get() = asyncDiffer.currentList
        set(value) = asyncDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        with(countries[position]) {
            holder.bind(this) {
                onItemSelectionListener?.let { click ->
                    click(this)
                }
            }
        }
    }


    override fun getItemCount() = countries.size
}