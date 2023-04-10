package com.example.plants.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.plants.R
import com.example.plants.databinding.FragmentSettingsBinding

class Settings: Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val changePasswordButton = binding.changePassword


        changePasswordButton.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_updateFragment)
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<ChangePassword>(R.id.shoppinHostFragment) //shoppingHostFragment id of frame
                addToBackStack("to_profile")
            }
        }






    }
}