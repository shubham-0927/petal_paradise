package com.example.plants.fragments.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.plants.R
import com.example.plants.databinding.FragmentProductDetailsBinding
import com.example.plants.databinding.FragmentProfileBinding
import com.example.plants.databinding.FragmentSearchBinding
import org.json.JSONException
import org.json.JSONObject

class ProductDetailsFragment:Fragment(R.layout.fragment_product_details) {
    private lateinit var binding: FragmentProductDetailsBinding

    companion object {
        const val ARG_PLANT_NAME = "rose"
        const val TREFLE_API_URL = "https://trefle.io/api/v1/plants/"
        const val TREFLE_API_KEY = "JVQ4vaC5o6bsgZRbm_m5je2iV6BYarJPXXKySuGpqsM"
    }
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

/*    companion object {
        fun newInstance(title: String): ProductDetailsFragment {
            val args = Bundle()
            args.putString("title", title)
            val fragment = ProductDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }*/

        var plantName = arguments?.getString(ARG_PLANT_NAME)

        plantName = "rose"
        // Request plant details from Trefle API
        val queue = Volley.newRequestQueue(requireContext())
        if (plantName != null) {
            val url = "https://trefle.io/api/v1/plants/search?token=JVQ4vaC5o6bsgZRbm_m5je2iV6BYarJPXXKySuGpqsM&q=jade"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val data = response.getJSONArray("data")

                    if (data.length() > 0) {
                        val plantDetails = data.getJSONObject(0)
                        Log.d("TAG", "JSON keys: ${plantDetails.keys().asSequence().toList()}")
                        /*val commonName = plantDetails.getString("common_name")
                        val habitats = plantDetails.getJSONArray("main_species").getJSONObject(0).getJSONArray("habitats")
                            .let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
                            .mapNotNull { it?.getString("name") }
                            .joinToString(", ")
                        val description = plantDetails.getString("wiki_description")*/

                        val commonName = plantDetails.optString("common_name", "Unknown")
                        val aboutPlant = "Genus: "+plantDetails.optString("genus", "Unknown") + " " +" Family:"+ plantDetails.optString("family_common_name", "Unknown")
                       /* val habitats = plantDetails.getJSONArray("main_species").getJSONObject(0).getJSONArray("habitats")
                            .let { 0.until(it.length()).map { i -> it.optJSONObject(i) } }
                            .mapNotNull { it?.getString("name") }
                            .joinToString(", ")
                        val mainSpecies = plantDetails.optJSONObject("main_species")
                        val imageURL = mainSpecies?.optJSONArray("images")?.optJSONObject(0)?.optString("url", "")

// Check if main_species exists before accessing its properties
                        val flower = mainSpecies?.optString("flower", "Unknown")
                        val fruit = mainSpecies?.optString("fruit", "Unknown")
                        val foliage = mainSpecies?.optString("foliage", "Unknown")
                        val scientificName = mainSpecies?.optString("scientific_name", "Unknown")*/

                        val scientificName = plantDetails.getString("scientific_name")
                        val genusName = plantDetails.getString("genus")
                        val familyName = plantDetails.getString("family")
                        val synonyms = plantDetails.getJSONArray("synonyms")
                        // Do something with the fetched details
                        binding.tvCommonName.text = commonName
                        binding.tvAboutPlant.text = aboutPlant + " synonyms" +synonyms
                        binding.tvScientificName.text = scientificName
                    } else {
                        // Handle the case where no plant details were found
                    }
                },
                { error ->
                    // Handle any errors
                }
            )

            queue.add(jsonObjectRequest)
        } else {
            // Handle the case where plantName is null
            Toast.makeText(context, "plant name not found", Toast.LENGTH_SHORT).show()
        }
    }
}