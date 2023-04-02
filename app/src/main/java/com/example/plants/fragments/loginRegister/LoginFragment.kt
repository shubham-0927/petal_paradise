package com.example.plants.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.activities.ShoppingActivity
import com.example.plants.databinding.FragmentLoginBinding
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials

class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setContentView(R.layout.activity_login_reg)
        Realm.init(context)

        // Initialize the MongoDB Realm app instance
        val appID = "application-0-yyhyb"
        val app = App(AppConfiguration.Builder(appID).build())

        // Find views
        val usernameEditText = binding.usernameEditText/*findViewById<EditText>(R.id.usernameEditText)*/
        val passwordEditText = binding.passwordEditText  /*findViewById<EditText>(R.id.passwordEditText)*/
        val loginButton =  binding.loginButton /*findViewById<Button>(R.id.loginButton)*/

        // Set click listener for login button
        loginButton.setOnClickListener {
            // Get the entered username and password
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val intent = Intent(context, ShoppingActivity::class.java)
            startActivity(intent)
//            activity?.finish()
            //checks for empty username
 /*           if (username != "") {

                // Authenticate the user with MongoDB Realm
                app.loginAsync(Credentials.emailPassword(username, password)) { result ->
                    if (result.isSuccess) {
                        // Successful login, navigate to main activity
                        val intent = Intent(context, ShoppingActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    } else {
                        // Failed login, display error message
                        val errorMessage = result.error.errorMessage
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(context, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            }*/
        }
    }
}