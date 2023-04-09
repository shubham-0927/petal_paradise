package com.example.plants.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.plants.R
import com.example.plants.databinding.ActivityMainBinding
import com.example.plants.fragments.shopping.CartFragment
import com.example.plants.fragments.shopping.HomeFragment
import com.example.plants.fragments.shopping.ProfileFragment
import com.example.plants.fragments.shopping.SearchFragment
import com.example.plants.viewmodel.UserDetailsViewModel

class ShoppingActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
        val fragmentManager = supportFragmentManager
//        val navController = findNavController(R.id.shoppinHostFragment)
//        binding.bottomNavigation.setupWithNavController(/*navController*/ findNavController(R.id.shoppinHostFragment))

        //fetch key value pair from login activity
        val email = intent.getStringExtra("email")
        val username = intent.getStringExtra("username")
        val mobilenumber = intent.getStringExtra("mobilenumber")
        val dob = intent.getStringExtra("dob")
        val address = intent.getStringExtra("address")
        val image = intent.getStringExtra("image")
        val viewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java)
        viewModel.setValues(email!!, username!!, mobilenumber!!, dob!!, address!!, image!!)
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment ->/*replaceFragment(HomeFragment())  */ fragmentManager.commit {
                    setReorderingAllowed(true)
                    // Replace whatever is in the fragment_container view with this fragment
                    replace<HomeFragment>(R.id.shoppinHostFragment)
                }
                R.id.searchFragment ->   fragmentManager.commit {
                    setReorderingAllowed(true)
                    // Replace whatever is in the fragment_container view with this fragment
                    replace<SearchFragment>(R.id.shoppinHostFragment)
                }
                R.id.cartFragment ->fragmentManager.commit {
                    setReorderingAllowed(true)
                    // Replace whatever is in the fragment_container view with this fragment
                    replace<CartFragment>(R.id.shoppinHostFragment)
                }
                R.id.profileFragment ->fragmentManager.commit {
                    setReorderingAllowed(true)
                    // Replace whatever is in the fragment_container view with this fragment
                    replace<ProfileFragment>(R.id.shoppinHostFragment)
                }

                else -> {

                }

            }
                 true
        }

    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.shoppinHostFragment,fragment)
        fragmentTransaction.commit()

   /*     // Create and commit a new transaction
        fragmentManager.commit {
            setReorderingAllowed(true)
            // Replace whatever is in the fragment_container view with this fragment
            replace<fragment>(R.id.shoppinHostFragment)
        }*/
    }

}