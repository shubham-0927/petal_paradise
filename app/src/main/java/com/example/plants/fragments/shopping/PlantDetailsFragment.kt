package com.example.plants.fragments.shopping


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
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
import com.example.plants.data.Plants
import com.example.plants.databinding.FragmentPlantDetailBinding
import io.realm.ImportFlag
import io.realm.Realm
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import java.util.*

class PlantDetailsFragment: Fragment(R.layout.fragment_plant_detail) {
    private lateinit var binding: FragmentPlantDetailBinding
    private lateinit var plantsciname: TextView
    private lateinit var searchbtn: ImageView
    private lateinit var plantImageView: ImageView
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
        plantsciname = binding.plantNameSci
        searchbtn = binding.websearch
        plantImageView = binding.imageView3
        val image = arguments?.getParcelable<Bitmap>("image")
        val scientificname = arguments?.getString("outputName")
        plantsciname.text = scientificname.toString()
        if (image != null){
            binding.imageView3.setImageBitmap(image)
        }else{
            Log.e("plantImage","empty response")
        }


        Realm.init( context)
        val appID = "application-0-yyhyb"
        val app = App(AppConfiguration.Builder(appID).build())
//        val realmApp :RealmApp.create(appConfig)
        val realm =Realm.getDefaultInstance()

        app.loginAsync(Credentials.anonymous()) { result ->
            if (result.isSuccess) {
                try {
                    val flexibleSyncConfig = SyncConfiguration.Builder(app.currentUser())
                        .waitForInitialRemoteData()
                        .initialSubscriptions { realm, subscriptions ->
                            /*subscriptions.add(
                                Subscription.create(
                                    realm.where<Plants>()
                                )
                            )*/
                        }.build()
                    val realm = Realm.getInstance(flexibleSyncConfig)

//                    val realm = Realm.getDefaultInstance()

                    try {
                        realm.executeTransactionAsync { transactionRealm ->
                            val plant = Plants().apply {
//                                _id = UUID.randomUUID().toString()
                                _id = "1"
                                name = "plant1"
                            }
                            Log.e("trnsaction","check point 1")
                            transactionRealm.insertOrUpdate(plant)
                            Log.e("trnsaction","check poiint 2")
                            transactionRealm.copyToRealmOrUpdate(plant,
                                ImportFlag.CHECK_SAME_VALUES_BEFORE_SET)
                            try {
                                transactionRealm.insertOrUpdate(plant)
                            }catch (exp : Exception){
                                Log.e("trnsaction","exception $exp")
                            }

                            val syncedObjects =
                                transactionRealm.where<Plants>().equalTo("name", "plant1").findAll()
//                        transactionRealm.insert(user)
                            syncedObjects.forEach { syncedObject ->
                                // Use copyToRealmOrUpdate() to insert or update objects in your local Realm
                                Log.i("Realm", "syncObjects $syncedObject ")
                                transactionRealm.copyToRealmOrUpdate(syncedObject)
                            }
                            val plant_res =
                                transactionRealm.where(Plants::class.java).equalTo("_id", "1")
                                    .findAll()
                            Log.i("transactionRealm", "Plant details: $plant_res ")
                        }
                    } catch (e: Exception) {
                        Log.e("transaction", "exception $e")
                    }
//                       activity?.finish()
                    realm.close()
                } catch (e: Exception) {
                    Log.e("loginSync", "exception $e")
                }
            }
        }

//        client.close()


        searchbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?g=${plantsciname.text}"))
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}