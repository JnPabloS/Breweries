package com.jnpablos.breweries.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jnpablos.breweries.R
import com.jnpablos.breweries.databinding.BreweryListItemBinding
import com.jnpablos.breweries.model.Brewery

class BreweriesAdapter (
    private val onItemClicked : (Brewery) -> Unit,
        ) : RecyclerView.Adapter<BreweriesAdapter.ViewHolder> () {

    private val listBreweries : MutableList<Brewery> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = BreweryListItemBinding.bind(view)
        private val context : Context = binding.root.context

        fun bind(brewery: Brewery) {
            with(binding){
                nameTextView.text = brewery.name
                cityTextView.text = brewery.city+" - "+brewery.state
                typeTextView.text = context.getString(R.string.type_of_brewery,brewery.breweryType)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brewery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listBreweries[position])
        holder.itemView.setOnClickListener { onItemClicked(listBreweries[position]) }
    }

    override fun getItemCount(): Int = listBreweries.size
    fun appendItems(newItems: MutableList<Brewery>) {
        listBreweries.clear()
        listBreweries.addAll(newItems)
        notifyDataSetChanged()
    }
}