package com.example.plants.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.Navigation
import com.example.plants.R
import com.example.plants.databinding.FragmentIntroductionBinding




class IntroductionFragment: Fragment(R.layout.fragment_introduction) {
    private lateinit var binding: FragmentIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startbtn = binding.buttonStart
//        val fragmentManager = getParentFragmentManager()
        startbtn.setOnClickListener{
/*            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.introductionFragment,LoginFragment()).commit()*/

            Navigation.findNavController(view).navigate(R.id.action_introductionFragment_to_loginFragment2)
        }
    }
}