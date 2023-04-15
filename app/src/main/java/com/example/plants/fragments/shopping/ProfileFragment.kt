package com.example.plants.fragments.shopping

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plants.R
import com.example.plants.databinding.FragmentProfileBinding
import com.example.plants.fragments.categories.About
import com.example.plants.fragments.categories.MyOrders
import com.example.plants.fragments.categories.Settings
import com.example.plants.fragments.categories.UpdateProfile
import com.example.plants.viewmodel.UserDetailsViewModel

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var myusername : TextView
    private lateinit var emailaddr : TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val updateProfileBtn = binding.updateProfile
        val myOrdersBtn = binding.myOrders
        val settingsBtn = binding.settings
        val aboutBtn = binding.about


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window : Window? = activity?.window
            window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.green_leaf)
        }
        //viewmodel to fetch values
        val viewModel = ViewModelProvider(requireActivity()).get(UserDetailsViewModel::class.java)
        myusername = binding.textView
        emailaddr = binding.textView2


        viewModel.email.observe(viewLifecycleOwner, { email->
            emailaddr.text = email
        })
        viewModel.username.observe(viewLifecycleOwner, Observer { username->
            myusername.text = username
        })


        updateProfileBtn.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_updateFragment)
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<UpdateProfile>(R.id.shoppinHostFragment) //shoppingHostFragment id of frame
                addToBackStack("to_profile")
            }
        }

        myOrdersBtn.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_updateFragment)
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<MyOrders>(R.id.shoppinHostFragment) //shoppingHostFragment id of frame
                addToBackStack("to_profile")
            }
        }

        settingsBtn.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_updateFragment)
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<Settings>(R.id.shoppinHostFragment) //shoppingHostFragment id of frame
                addToBackStack("to_profile")
            }
        }

        aboutBtn.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_updateFragment)
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<About>(R.id.shoppinHostFragment) //shoppingHostFragment id of frame
                addToBackStack("to_profile")
            }
        }
    }
}