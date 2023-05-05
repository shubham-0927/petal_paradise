package com.example.plants.fragments.loginRegister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.activities.ShoppingActivity
import com.example.plants.data.Users
import com.example.plants.databinding.FragmentLoginBinding
import io.realm.Realm
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

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
        context?.let { Realm.init(it) }

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


            //call viewmodel to store values
//            val viewModel = ViewModelProvider(requireActivity()).get(UserDetailsViewModel::class.java)

/*            val intent = Intent(context, ShoppingActivity::class.java)
            startActivity(intent)*/
//            activity?.finish()
            //checks for empty username
            if (username.isNotEmpty() && password.isNotEmpty()) {

                // Authenticate the user with MongoDB Realm
                binding.loginTV.visibility = View.INVISIBLE
                binding.loginProgessBar.visibility = View.VISIBLE
                app.loginAsync(Credentials.emailPassword(username,password)){
                    res -> if(res.isSuccess){
                    app.loginAsync(Credentials.anonymous()) { result ->
                        if (result.isSuccess) {

                            val flexibleSyncConfig = SyncConfiguration.Builder(app.currentUser())
                                .waitForInitialRemoteData()
                                .initialSubscriptions { realm, subscriptions ->
                                    /* subscriptions.add(
                                         Subscription.create(
                                             realm.where<Users>()
                                         )
                                     )*/
                                }.build()
                            val realm = Realm.getInstance(flexibleSyncConfig)


                            try {
                                realm.executeTransactionAsync { transactionRealm ->
                                    /*val user = Users().apply {
                                        _id = "2"
                                        email = "qwer@gmail.com"
                                    }
                                    transactionRealm.insert(user)*/
                                    val syncedObjects = transactionRealm.where<Users>().equalTo("email",username).findAll()
//                        transactionRealm.insert(user)
                                    syncedObjects.forEach { syncedObject ->
                                        // Use copyToRealmOrUpdate() to insert or update objects in your local Realm
                                        Log.i("Realm","syncObjects $syncedObject ")
                                        transactionRealm.copyToRealmOrUpdate(syncedObject)
                                    }
                                    val user = transactionRealm.where(Users::class.java).equalTo("_id","2").findAll()
                                    Log.i("transactionRealm","users details: $user ")
                                }
                            }catch (e :Exception){
                                Log.e("transaction","exception $e")
                            }

                            val user: User? = app.currentUser()!!
                            val email = user?.profile?.email
                            // val realm = Realm.getDefaultInstance()
                            val task = realm.where(Users::class.java).equalTo("email", username).findFirst()

                            // Successful login, navigate to main activity and pass user details to shopping activity
                            Log.v("EXAMPLE", "Fetched task username: ${task?.username}")
                            Log.v("EXAMPLE", "Fetched task: $task")
                            Log.v("EXAMPLE", "Fetched object by primary key: $email")
                            val intent = Intent(context, ShoppingActivity::class.java)
                            intent.putExtra("email",username)
                            intent.putExtra("username",task?.username)
                            intent.putExtra("mobilenumber",task?.mobilenumber)
                            intent.putExtra("dob",task?.dob)
                            intent.putExtra("address",task?.address)
                            intent.putExtra("image",""/*task?.image*/)
                            startActivity(intent)
                            binding.loginTV.visibility = View.VISIBLE
                            binding.loginProgessBar.visibility = View.INVISIBLE
//                       activity?.finish()
                            realm.close()
                        } else {
                            // Failed login, display error message
                            binding.loginTV.visibility = View.VISIBLE
                            binding.loginProgessBar.visibility = View.INVISIBLE
                            val errorMessage = result.error.errorMessage
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else {
                    // Failed login, display error message
                    binding.loginTV.visibility = View.VISIBLE
                    binding.loginProgessBar.visibility = View.INVISIBLE
                    val errorMessage = res.error.errorMessage
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
                    }
                }else{
                Toast.makeText(context, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            }

        }
    }
}