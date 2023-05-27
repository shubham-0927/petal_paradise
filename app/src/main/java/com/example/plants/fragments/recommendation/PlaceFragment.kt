package com.example.plants.fragments.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.plants.R
import com.example.plants.databinding.FragmentPlaceBinding

class PlaceFragment: Fragment(R.layout.fragment_place) {
    private lateinit var binding: FragmentPlaceBinding
    private lateinit var indoor : TextView
    private lateinit var outdoor : TextView
    private lateinit var back : ImageView
    private lateinit var next : ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        indoor = binding.indoor
        outdoor = binding.outdoor
        back = binding.icon2
        next = binding.icon1
        indoor.setOnClickListener {
            indoor.setBackgroundColor(resources.getColor(R.color.g_blue100))
            val bundle = Bundle()
            bundle.putString("place","in" )
            val fragmentB = CareFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)

            }
        }
        outdoor.setOnClickListener {
            outdoor.setBackgroundColor(resources.getColor(R.color.g_blue100))
            val bundle = Bundle()
            bundle.putString("place","out" )
            val fragmentB = CareFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)

            }
        }
        next.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("place","any" )
            val fragmentB = CareFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)
            }
        }

    }
}