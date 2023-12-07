package com.example.plants.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.R
import com.example.plants.adapters.FlowerCardAdapter
import com.example.plants.adapters.FlowerProductAdapter
import com.example.plants.data.FlowerCardData
import com.example.plants.data.PlantSellData
import com.example.plants.databinding.FragmentFlowerBinding

class FlowerFragment: Fragment(R.layout.fragment_flower){
    private lateinit var binding: FragmentFlowerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var cardAdapter: FlowerCardAdapter
    private lateinit var productAdapter: FlowerProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlowerBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvSpecialProducts
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val cardDataList = listOf(
            FlowerCardData(R.drawable.ixora, "Ixora","₹160","50%","₹80"),
            FlowerCardData(R.drawable.parijat, "Parijat","₹160","50%","₹80"),
            FlowerCardData(R.drawable.chrysanthemum, "Chrysanthemum","₹160","50%","₹80"),
        )
        cardAdapter = FlowerCardAdapter(requireContext(), cardDataList)
        recyclerView.adapter = cardAdapter


        recyclerView2 = binding.rvBestProducts
//        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val plantDataList = listOf(
            PlantSellData(R.drawable.marigold, "Marigold","₹80"),
            PlantSellData(R.drawable.periwinkle, "Periwinkle","₹40"),
            PlantSellData(R.drawable.petunia, "Petunia","₹80"),
            PlantSellData(R.drawable.p3, "Pothos","₹80"),
            PlantSellData(R.drawable.p4, "Boston Fern","₹80"),
            PlantSellData(R.drawable.p5, "Aloe Vera","₹80"),
            PlantSellData(R.drawable.p6, "Spider Plant","₹80")
        )
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView2.layoutManager = layoutManager

        val adapter = FlowerProductAdapter(requireContext(),plantDataList,childFragmentManager)
        recyclerView2.adapter = adapter
/*
        val maincatRV = binding.extraLL
        maincatRV.setOnClickListener {
            fragmentManager?.commit {
                setReorderingAllowed(true)
                // Replace whatever is in the fragment_container view with this fragment
                replace<ProductDetailsFragment>(R.id.shoppinHostFragment)
                addToBackStack(null)
            }
        }
*/

    }
}