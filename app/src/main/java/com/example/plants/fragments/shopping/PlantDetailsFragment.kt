package com.example.plants.fragments.shopping

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.databinding.FragmentPlantDetailBinding
import com.example.plants.databinding.FragmentSearchBinding

class PlantDetailsFragment: Fragment(R.layout.fragment_plant_detail) {
    private lateinit var binding: FragmentPlantDetailBinding
    private lateinit var plantsciname: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantDetailBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plantsciname = binding.plantNameSci
        plantsciname.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?g=${plantsciname.text}"))
            startActivity(intent)
        }
    }
}