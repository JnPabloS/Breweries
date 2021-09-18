package com.jnpablos.breweries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jnpablos.breweries.R
import com.jnpablos.breweries.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args : DetailFragmentArgs by navArgs()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val root: View = binding.root

        with(binding) {
            val brewery = args.brewery
            nameTextView.text = brewery.name
            cityTextView.text = brewery.city + " - " + brewery.state
            streetTextView.text = brewery.street
            phoneTextView.text = brewery.phone
            websiteTextView.text = brewery.websiteUrl
        }
        return root
    }

}