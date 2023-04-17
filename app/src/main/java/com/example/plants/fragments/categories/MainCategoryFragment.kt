package com.example.plants.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.R
import com.example.plants.adapters.CardAdapter
import com.example.plants.adapters.ProductAdapter
import com.example.plants.data.CardData
import com.example.plants.data.PlantSellData
import com.example.plants.databinding.FragmentMainCategoryBinding
import com.example.plants.databinding.FragmentSearchBinding
import com.example.plants.fragments.shopping.PlantDetailsFragment

class MainCategoryFragment: Fragment(R.layout.fragment_main_category) {
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var cardAdapter: CardAdapter
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainCategoryBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvSpecialProducts
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val cardDataList = listOf(
            CardData(R.drawable.plant1, "Plant 1"),
            CardData(R.drawable.plant1, "Plant 2"),
            CardData(R.drawable.plant1, "Plant 3"),
            CardData(R.drawable.plant1, "Plant 4"),
            CardData(R.drawable.plant1, "Plant 5"),
        )
        cardAdapter = CardAdapter(requireContext(), cardDataList)
        recyclerView.adapter = cardAdapter


        recyclerView2 = binding.rvBestProducts
//        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val plantDataList = listOf(
            PlantSellData(R.drawable.plant1, "Plant 1","$200"),
            PlantSellData(R.drawable.plant1, "Plant 2","$200"),
            PlantSellData(R.drawable.plant1, "Plant 3","$200"),
            PlantSellData(R.drawable.plant1, "Plant 4","$200"),
            PlantSellData(R.drawable.plant1, "Plant 5","$200"),
            PlantSellData(R.drawable.plant1, "Plant 6","$200"),
            PlantSellData(R.drawable.plant1, "Plant 7","$200")
        )
/*        productAdapter = ProductAdapter(requireContext(), plantDataList)
        recyclerView2.adapter = productAdapter*/
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView2.layoutManager = layoutManager

//        val cardRecyclerView = view.findViewById<RecyclerView>(R.id.card_recycler_view)
//        cardRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
// pass childFragmentManager as a parameter
//        cardRecyclerView.adapter = cardAdapter
        val adapter = ProductAdapter(requireContext(),plantDataList,childFragmentManager)
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