package com.example.plants.fragments.loginRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.databinding.FragmentLoginBinding
import com.example.plants.databinding.FragmentRegisterBinding
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import kotlinx.coroutines.runBlocking

class RegistrationFragment: Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        Realm.init(context)
        val appID = "application-0-yyhyb"
        val app = App(AppConfiguration.Builder(appID).build())
        val username = binding.usernameET.text.toString()

        val email = binding.userEmailET.text.toString()
        val password = binding.password.text.toString()
        val registerbutton= binding.register
        val user = com.example.plants.data.User(username,email,password)
        val creadentials = Credentials.emailPassword(email, password)*/
/*        registerbutton.setOnLongClickListener(){
            app.emailPasswordAuth.registerUserAsync(creadentials).addOnCompleteListener{
            task -> if(task.isSuccessful){
            val user = User(email,password,name)}
            else{
            }
            }
        }*/
    }
}