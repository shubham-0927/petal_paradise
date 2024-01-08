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
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.data.PlantInfo
import com.example.plants.databinding.FragmentPlantDetailBinding
import io.realm.Realm
import io.realm.mongodb.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface PlantApi {
    @GET("/getPlants/")
    fun getPlantInfo(@Query("name") scientificName: String): Call<PlantInfo>
}
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
        val plantCategoryTextView: TextView = binding.category
        val plantSizeTextView: TextView = binding.size
        val plantNameSciTextView: TextView = binding.plantNameSci
        val commonNameTextView: TextView = binding.commonName
        val descriptionTextView: TextView = binding.description
        val sunlightTextView: TextView = binding.sunlight
        val waterTextView: TextView = binding.water
        val tempTextView: TextView = binding.temp
        val spaceTextView: TextView = binding.space
        val image = arguments?.getParcelable<Bitmap>("image")
        val scientificname = arguments?.getString("outputName")
//        plantsciname.text = scientificname.toString()
        if (image != null){
            binding.imageView3.setImageBitmap(image)
        }else{
            Log.e("plantImage","empty response")
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://plantappbackend.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

// Create the API service
        val plantApi = retrofit.create(PlantApi::class.java)
        val loadingProgressBar: ProgressBar = view.findViewById(R.id.loadingProgressBar)
        loadingProgressBar.visibility = View.VISIBLE

// Make the API request
        val scientificName = arguments?.getString("outputName")
        if (scientificName != null) {
            val call = plantApi.getPlantInfo(scientificName)
//            val call = plantApi.getPlantInfo("Rose")
            call.enqueue(object : Callback<PlantInfo> {
                override fun onResponse(call: Call<PlantInfo>, response: Response<PlantInfo>) {
                    loadingProgressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        val plantInfo = response.body()
                        if (plantInfo != null) {
                            plantCategoryTextView.text = plantInfo.category

                            plantSizeTextView.text = plantInfo.size
                            plantNameSciTextView.text = plantInfo.commonname
                            commonNameTextView.text = plantInfo.name
                            descriptionTextView.text = plantInfo.desc
                            sunlightTextView.text = plantInfo.sunlight
                            waterTextView.text = plantInfo.watering
                            tempTextView.text = plantInfo.temperature
                            spaceTextView.text = plantInfo.space
                        }
                    } else {
                        // Handle the error
                        Log.e("API Request", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<PlantInfo>, t: Throwable) {
                    // Handle the failure
                    Log.e("API Request", "Failure: ${t.message}")
                }
            })
        } else {
            Log.e("ScientificName", "Scientific name is null")
        }


        searchbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?g=${plantsciname.text}"))
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}