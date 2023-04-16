package com.example.plants.fragments.shopping

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.data.Users
import com.example.plants.databinding.FragmentPlantDetailBinding
import com.example.plants.databinding.FragmentSearchBinding
import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients


import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

import org.bson.Document

class PlantDetailsFragment: Fragment(R.layout.fragment_plant_detail) {
    private lateinit var binding: FragmentPlantDetailBinding
    private lateinit var plantsciname: TextView
    private lateinit var searchbtn: ImageView
    lateinit var uiThreadRealm: Realm
    lateinit var app: App
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantDetailBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window : Window? = activity?.window
            window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.green_100)
        }
       /* Realm.init(context)
        val appID = "application-0-yyhyb"
        app = App(AppConfiguration.Builder(appID)
            .build())
        app.loginAsync(Credentials.anonymous()){result->
            if(result.isSuccess){
                Log.v("message","login success $result")
                val realm :Realm
                val user: User? = app.currentUser()!!
                val partitionValue: String = "PROJECT 0"
                val config = SyncConfiguration.defaultConfig(user, partitionValue)
                uiThreadRealm = Realm.getInstance(config)
                *//* val uri = "mongodb+srv://Master:%30%5FRAtlas%5Fadms%40PRS@atlascluster.npc8jrq.mongodb.net/?retryWrites=true&w=majority"
                 val databaseName = "patalParadisedb"
                 val collectionName = "plants"
                 plantsciname = binding.plantNameSci
                 val client = MongoClients.create(uri)
                 val database = client.getDatabase(databaseName)
                 val collection = database.getCollection(collectionName)
                 val query =Document("name",plantsciname.text.toString())
                 val result = collection.find(query).toList()*//*


                realm = uiThreadRealm
                val tasks : RealmResults<Users> = realm.where<Users>().findAllAsync()
                val thisuser = realm.where<Users>().equalTo("email","user2@gmail.com").findFirst()
                val mytask = tasks.where().equalTo("email", "user2@gmail.com").findFirst()

                Log.d("V","User name fetched : $thisuser")

                binding.commonName.text = mytask?.username.toString()
                searchbtn = binding.websearch
                searchbtn.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?g=${plantsciname.text}"))
                    startActivity(intent)
                }
                realm.close()
            }

        }*/

//        client.close()

        plantsciname = binding.plantNameSci
        searchbtn = binding.websearch
        searchbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?g=${plantsciname.text}"))
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}