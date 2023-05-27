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
import com.example.plants.databinding.FragmentCareBinding

class CareFragment: Fragment(R.layout.fragment_care) {
    private lateinit var binding: FragmentCareBinding
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
        binding = FragmentCareBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        low = binding.low
        mid = binding.mid
        high = binding.high
        back = binding.icon2
        next = binding.icon1
        val place = arguments?.getString("place")
        low.setOnClickListener {
            low.setBackgroundColor(resources.getColor(R.color.g_blue100))
            val bundle = Bundle()
            bundle.putString("place",place)
            bundle.putString("care","low")
            val fragmentB = SpaceFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)
            }
        }
        mid.setOnClickListener {
            mid.setBackgroundColor(resources.getColor(R.color.g_blue100))
            val bundle = Bundle()
            bundle.putString("place",place)
            bundle.putString("care","mid")
            val fragmentB = SpaceFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)

            }
        }
        high.setOnClickListener {
            high.setBackgroundColor(resources.getColor(R.color.g_blue100))

            val bundle = Bundle()
            bundle.putString("place",place)
            bundle.putString("care","high")
            val fragmentB = SpaceFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)

            }
        }
        next.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("place","any" )
            bundle.putString("care","any" )
            val fragmentB = SpaceFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)
            }
        }
        back.setOnClickListener {
            val fragmentB = PlaceFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)
            }
        }
    }
}