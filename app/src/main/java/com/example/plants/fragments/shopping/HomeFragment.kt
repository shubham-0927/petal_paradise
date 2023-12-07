package com.example.plants.fragments.shopping

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
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
            FlowerFragment(),
            IndoorFragment(),
            BonsaiFragment(),

        )

        val viewpager2Adapter = HomeViewpagerAdapter(categoriesFragments, childFragmentManager,lifecycle)
        binding.viewpagerHome.adapter = viewpager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome){ tab, position ->
            when (position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Flower"
                2 -> tab.text = "Indoor"
                3 -> tab.text = "Bonsai"

            }
        }.attach()
        val clickableView = binding.extraLL
        clickableView.setOnClickListener {
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<ProductDetailsFragment>(R.id.shoppinHostFragment)
                addToBackStack(null)
            }
        }
    }
}