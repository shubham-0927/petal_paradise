package com.example.plants.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.R
import com.example.plants.adapters.BonsaiCardAdapter
import com.example.plants.adapters.BonsaiProductAdapter
import com.example.plants.data.BonsaiPlantSellData
import com.example.plants.databinding.FragmentBonsaiBinding

class BonsaiFragment: Fragment(R.layout.fragment_bonsai){
    private lateinit var binding: FragmentBonsaiBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var cardAdapter: BonsaiCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonsaiBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView2 = binding.rvBestProducts
//        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val plantDataList = listOf(
            BonsaiPlantSellData(R.drawable.adenium, "Adenium","₹80"),
            BonsaiPlantSellData(R.drawable.adeniumpink, "Adenium Pink","₹40"),
            BonsaiPlantSellData(R.drawable.ficusbonsai, "Ficusbonsai","₹80"),
            BonsaiPlantSellData(R.drawable.murraya, "Murraya","₹80"),
            BonsaiPlantSellData(R.drawable.schefflera, "Schefflera","₹80"),

        )

        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView2.layoutManager = layoutManager

        val adapter = BonsaiProductAdapter(requireContext(),plantDataList,childFragmentManager)
        recyclerView2.adapter = adapter

    }
}