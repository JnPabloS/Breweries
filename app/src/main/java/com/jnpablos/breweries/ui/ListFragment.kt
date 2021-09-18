package com.jnpablos.breweries.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jnpablos.breweries.databinding.FragmentListBinding
import com.jnpablos.breweries.model.BreweriesList
import com.jnpablos.breweries.model.Brewery
import com.jnpablos.breweries.server.ApiService
import retrofit2.Call
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var breweriesAdapter: BreweriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        val root: View = binding.root

        breweriesAdapter = BreweriesAdapter( onItemClicked = { onBreweryClicked(it)})
        binding.breweriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = breweriesAdapter
            setHasFixedSize(false)
        }

        loadBreweries()
        return root
    }

    private fun onBreweryClicked(brewery: Brewery) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(brewery))
    }

    private fun loadBreweries() {
        ApiService.create()
            .getBreweries()
            .enqueue(object :retrofit2.Callback<BreweriesList>{
                override fun onFailure(call: Call<BreweriesList>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }

                override fun onResponse(call: Call<BreweriesList>, response: Response<BreweriesList>) {
                    if (response.isSuccessful){
                        val listBreweries : MutableList<Brewery> = response.body()?.toMutableList() as MutableList<Brewery>
                        breweriesAdapter.appendItems(listBreweries)
                    }
                }
            })
    }
}