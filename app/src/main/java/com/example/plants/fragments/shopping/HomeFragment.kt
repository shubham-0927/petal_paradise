package com.example.plants.fragments.shopping

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.adapters.HomeViewpagerAdapter
import com.example.plants.databinding.FragmentHomeBinding
import com.example.plants.fragments.categories.*
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            val window : Window? = activity?.window
            window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.white)
        }
        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            IndoorFragment(),
            OutdoorFragment(),
            BonsaiFragment(),
            FlowerFragment()
        )

        val viewpager2Adapter = HomeViewpagerAdapter(categoriesFragments, childFragmentManager,lifecycle)
        binding.viewpagerHome.adapter = viewpager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome){ tab, position ->
            when (position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Indoor"
                2 -> tab.text = "Outdoor"
                3 -> tab.text = "Bonsai"
                4 -> tab.text = "Flower"
            }
        }.attach()
    }
}