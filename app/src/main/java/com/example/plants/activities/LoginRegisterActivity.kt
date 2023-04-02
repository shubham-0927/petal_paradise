package com.example.plants.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plants.databinding.ActivityLoginRegBinding

class LoginRegisterActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityLoginRegBinding.inflate(layoutInflater)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        supportFragmentManager.beginTransaction().replace(R.id.introductionFragment,LoginFragment()).commit()
//        setContentView(R.layout.activity_login_reg)
/*        Realm.init(this)

        // Initialize the MongoDB Realm app instance
        val appID = "application-0-yyhyb"
        val app = App(AppConfiguration.Builder(appID).build())

        // Find views
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Set click listener for login button
        loginButton.setOnClickListener {
            // Get the entered username and password
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username != "") {

                // Authenticate the user with MongoDB Realm
                app.loginAsync(Credentials.emailPassword(username, password)) { result ->
                    if (result.isSuccess) {
                        // Successful login, navigate to main activity
                        val intent = Intent(this, ShoppingActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Failed login, display error message
                        val errorMessage = result.error.errorMessage
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }*/

    }
}