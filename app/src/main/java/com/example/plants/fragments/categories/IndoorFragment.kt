package com.example.plants.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.R
import com.example.plants.adapters.IndoorCardAdapter
import com.example.plants.adapters.IndoorProductAdapter
import com.example.plants.data.IndoorPlantSellData
import com.example.plants.databinding.FragmentIndoorBinding

class IndoorFragment: Fragment(R.layout.fragment_indoor){
    private lateinit var binding: FragmentIndoorBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var cardAdapter: IndoorCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndoorBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*recyclerView = binding.rvSpecialProducts
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val cardDataList = listOf(
            FlowerCardData(R.drawable.ixora, "Ixora","₹160","50%","₹80"),
            FlowerCardData(R.drawable.parijat, "Parijat","₹160","50%","₹80"),
            FlowerCardData(R.drawable.chrysanthemum, "Chrysanthemum","₹160","50%","₹80"),
        )
        cardAdapter = FlowerCardAdapter(requireContext(), cardDataList)
        recyclerView.adapter = cardAdapter
*/

        recyclerView2 = binding.rvBestProducts
//        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val plantDataList = listOf(
            IndoorPlantSellData(R.drawable.araliagreen, "Araliagreen","₹80"),
            IndoorPlantSellData(R.drawable.poinsettia, "Poinsettia","₹40"),
            IndoorPlantSellData(R.drawable.areca, "Areca","₹80"),
            IndoorPlantSellData(R.drawable.crotonpetra, "Crotonpetra","₹80"),
            IndoorPlantSellData(R.drawable.dracaenaruby, "dracaenaruby","₹80"),
            IndoorPlantSellData(R.drawable.p6, "Spider Plant","₹80")
        )

        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView2.layoutManager = layoutManager

        val adapter = IndoorProductAdapter(requireContext(),plantDataList,childFragmentManager)
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