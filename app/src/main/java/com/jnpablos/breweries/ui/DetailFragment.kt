package com.jnpablos.breweries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jnpablos.breweries.R
import com.jnpablos.breweries.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args : DetailFragmentArgs by navArgs()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var breweryName : String
    private lateinit var lat : String
    private lateinit var lon : String

    private val callback = OnMapReadyCallback { googleMap ->

        val brwry = LatLng(lat.toDouble(), lon.toDouble())
        googleMap.addMarker(MarkerOptions().position(brwry).title(breweryName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brwry,15F))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val root: View = binding.root

        with(binding) {

            val brewery = args.brewery
            lat = brewery.latitude.toString()
            lon = brewery.longitude.toString()
            breweryName = brewery.name.toString()

            nameTextView.text = breweryName
            cityTextView.text = getString(R.string.city, brewery.city + " - " + brewery.state)
            streetTextView.text = getString(R.string.street, brewery.street ?: "")
            phoneTextView.text = getString(R.string.phone, brewery.phone ?: "")
            websiteTextView.text = getString(R.string.website, brewery.websiteUrl ?: "")
            ubicationTextView.text = getString(R.string.ubication, "")
        }

        if (lat != "null") {
            val smf: SupportMapFragment =
                childFragmentManager.findFragmentById(R.id.ubicat) as SupportMapFragment
            smf.getMapAsync(callback)
        } else {
            binding.ubicationTextView.text = getString(R.string.ubication, "unknown")
        }

        return root
    }
}