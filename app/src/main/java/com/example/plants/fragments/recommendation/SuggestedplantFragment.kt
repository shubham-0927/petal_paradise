package com.example.plants.fragments.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.databinding.FragmentSuggestedplantBinding

class SuggestedplantFragment: Fragment(R.layout.fragment_suggestedplant) {
    private lateinit var binding: FragmentSuggestedplantBinding
    private lateinit var low: TextView
    private lateinit var mid: TextView
    private lateinit var high: TextView
    private lateinit var back : ImageView
    private lateinit var next : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuggestedplantBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  low = binding.low
        mid = binding.mid
        high = binding.high
        back = binding.icon2
        next = binding.icon1*/
        val place = arguments?.getString("place")
        val care = arguments?.getString("care")
    }
}