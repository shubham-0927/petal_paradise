package com.example.plants.fragments.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.databinding.FragmentProductDetailsBinding
import com.example.plants.databinding.FragmentProfileBinding
import com.example.plants.databinding.FragmentSearchBinding

class ProductDetailsFragment:Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun newInstance(title: String): ProductDetailsFragment {
            val args = Bundle()
            args.putString("title", title)
            val fragment = ProductDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}