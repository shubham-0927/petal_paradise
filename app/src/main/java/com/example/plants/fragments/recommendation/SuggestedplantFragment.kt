package com.example.plants.fragments.recommendation

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.data.PlantInfo
import com.example.plants.databinding.FragmentSuggestedplantBinding
import com.example.plants.fragments.shopping.PlantApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PlantApi {
    @GET("/getPlants/")
    fun getPlantInfo(@Query("name") scientificName: String): Call<PlantInfo>
}

class SuggestedplantFragment: Fragment(R.layout.fragment_suggestedplant) {
    private lateinit var binding: FragmentSuggestedplantBinding
    private lateinit var low: TextView
    private lateinit var mid: TextView
    private lateinit var high: TextView
    private lateinit var back : ImageView
    private lateinit var next : ImageView
    private lateinit var linearLayout: LinearLayout
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuggestedplantBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://plantappbackend.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val plantApi = retrofit.create(PlantApi::class.java)
        val loadingProgressBar: ProgressBar = view.findViewById(R.id.loadingProgressBar)
        loadingProgressBar.visibility = View.VISIBLE
        handler.postDelayed({
            // Show the RecyclerView after the delay
            loadingProgressBar.visibility = View.INVISIBLE
        }, 1000)
        linearLayout = view.findViewById(R.id.linearlayout)
        linearLayout.visibility = View.INVISIBLE
        handler.postDelayed({
            // Show the LinearLayout after the delay
            linearLayout.visibility = View.VISIBLE
        }, 1000)


      /*  low = binding.low
        mid = binding.mid
        high = binding.high
        back = binding.icon2
        next = binding.icon1*/
        val place = arguments?.getString("place")
        val care = arguments?.getString("care")
    }
}