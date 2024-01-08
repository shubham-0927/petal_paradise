package com.example.plants.fragments.recommendation

import android.os.Build
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.plants.R
import com.example.plants.databinding.FragmentPlaceBinding
import com.google.android.material.snackbar.Snackbar

class PlaceFragment: Fragment(R.layout.fragment_place) {
    private lateinit var binding: FragmentPlaceBinding
    private lateinit var indoor : CardView
    private lateinit var outdoor : CardView
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window : Window? = activity?.window
            window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.green_100)
        }
        indoor = binding.indoor
        outdoor = binding.outdoor
//        back = binding.icon2
//        next = binding.icon1
        indoor.setOnClickListener{
            binding.indoor.setCardBackgroundColor(ContextCompat.getColorStateList(requireContext(),R.color.green_100))
            binding.textView7.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            Snackbar.make(view, "Indoor is Clicked", Snackbar.LENGTH_SHORT).show()

        }
        outdoor.setOnClickListener{
            binding.outdoor.setCardBackgroundColor(ContextCompat.getColorStateList(requireContext(),R.color.green_100))
            binding.textView5.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            Snackbar.make(view, "Outdoor is Clicked", Snackbar.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putString("place","in" )
            val fragmentB = CareFragment()
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace(R.id.shoppinHostFragment, fragmentB)
            }
        }
//        indoor.setOnClickListener {
//            indoor.setBackgroundColor(resources.getColor(R.color.g_blue100))
//            val bundle = Bundle()
//            bundle.putString("place","in" )
//            val fragmentB = CareFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//
//            }
//        }
//        outdoor.setOnClickListener {
//            outdoor.setBackgroundColor(resources.getColor(R.color.g_blue100))
//            val bundle = Bundle()
//            bundle.putString("place","out" )
//            val fragmentB = CareFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//
//            }
//        }
//        next.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("place","any" )
//            val fragmentB = CareFragment()
//            fragmentManager?.commit {
//                setReorderingAllowed(true)
//                // Replace whatever is in the fragment_container view with this fragment
//                replace(R.id.shoppinHostFragment, fragmentB)
//            }
//        }

    }
}