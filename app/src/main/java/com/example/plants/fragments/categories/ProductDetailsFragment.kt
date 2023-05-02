package com.example.plants.fragments.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.plants.R
import com.example.plants.databinding.FragmentProductDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.chromium.base.ThreadUtils.runOnUiThread
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class ProductDetailsFragment:Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var plantScienticficName: TextView
    private lateinit var plantCommonName : TextView

    companion object;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }


    @SuppressLint("IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantCommonName = binding.tvCommonName
        plantScienticficName = binding.tvScientificName
        // Use a coroutine to make the network request in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Make a GET request to the API endpoint
                val url = URL("https://plants-api-production-f1f2.up.railway.app/api/plants")
                val jsonString = url.readText()
//                Log.d("TAG", "json fetched: $jsonString")

                try {
                    val jsonObject = JSONObject(jsonString)
                    val plantsArray = jsonObject.getJSONArray("plants")
                    var plantDesc = ""
                    var plantname = ""
                    var plantSci = ""
                    var plantSunlight = ""
                    var plantWatering =""
                    var plantTemp = ""
                    var plantSpace = ""
                    for (i in 0 until plantsArray.length()) {
                        val plantObject = plantsArray.getJSONObject(i)
                        val name = plantObject.getString("name")
                        val desc = plantObject.getString("desc")
                        val sci = plantObject.getString("commonname")
                        val sunlight = plantObject.getString("sunlight")
                        val water = plantObject.getString("watering")
                        val temp = plantObject.getString("temperature")
                        val space = plantObject.getString("space")
                        if (sci == "Asplenium nidus") {
                            plantDesc = desc
                            plantname = name
                            plantSci = sci
                            plantSunlight = sunlight
                            plantWatering = water
                            plantTemp = temp
                            plantSpace = space
                            break
                        }
                    }
                    runOnUiThread{
                        plantCommonName.text = plantname
                        binding.tvAboutPlant.text = plantDesc
                        plantScienticficName.text = plantSci
                        binding.sunlight.text = plantSunlight
                        binding.watering.text = plantWatering
                        binding.temperature.text = plantTemp
                        binding.space.text = plantSpace
                    }

                } catch (e: JSONException) {
                    Log.e("JSON Error", e.message ?: "Unknown error occurred")
                }

            }catch (e :Exception){
                Log.d("E", "exception $e")
            }
        }
    }
}