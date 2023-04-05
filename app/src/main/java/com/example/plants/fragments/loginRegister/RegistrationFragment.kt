package com.example.plants.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plants.R
import com.example.plants.data.UserData
import com.example.plants.databinding.FragmentRegisterBinding
import com.example.plants.viewmodel.RegisterViewModel
import com.example.plants.viewmodel.RegisterViewModelFactory
import io.realm.Realm
import io.realm.kotlin.mongodb.App

import io.realm.mongodb.AppConfiguration

class RegistrationFragment: Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var app: App
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view :View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        Realm.init( context)
        val appID = "application-0-yyhyb"
        val app = io.realm.mongodb.App(AppConfiguration.Builder(appID).build())
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(app)).get(RegisterViewModel::class.java)
        binding.register.setOnClickListener{
            val email  = binding.userEmailET.text.toString()
            val password = binding.password.text.toString()
            val userData = UserData(email,password)
            viewModel.registerUsers(userData)

        }
        viewModel.registrationResult.observe(viewLifecycleOwner, { result ->
            result.onSuccess {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }.onFailure {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Realm.init(context)
        val appID = "application-0-yyhyb"
        val app = App(AppConfiguration.Builder(appID).build())
        val username = binding.usernameET.text.toString()

        val email = binding.userEmailET.text.toString()
        val password = binding.password.text.toString()
        val registerbutton= binding.register
        val user = com.example.plants.data.UserData(username,email,password)
        val creadentials = Credentials.emailPassword(email, password)
*//*        registerbutton.setOnClickListener(){
            app.emailPasswordAuth.registerUserAsync(creadentials).addOnCompleteListener{
            task -> if(task.isSuccessful){
            val user = User(email,password,name)}
            else{
            }
            }
        }*//*
    }*/
}