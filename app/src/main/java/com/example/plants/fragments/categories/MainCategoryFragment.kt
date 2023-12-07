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
import com.example.plants.adapters.CardAdapter
import com.example.plants.adapters.ProductAdapter
import com.example.plants.data.CardData
import com.example.plants.data.PlantSellData
import com.example.plants.databinding.FragmentMainCategoryBinding

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

//        val appID = "application-0-yyhyb"
//        val app = App(AppConfiguration.Builder(appID).build())

        /*try {
            app.loginAsync(Credentials.anonymous()){
                    result -> if(result.isSuccess){
                try {
                    val flexibleSyncConfig = SyncConfiguration.Builder(app.currentUser())
                        .waitForInitialRemoteData()
                        .initialSubscriptions { realm, subscriptions ->
                        }.build()
                    val realm = Realm.getInstance(flexibleSyncConfig)
                    realm.executeTransactionAsync { transactionRealm ->
                        *//*val syncedObjects = transactionRealm.where<Users>().findAll()
//                        transactionRealm.insert(user)
                        syncedObjects.forEach { syncedObject ->
                            // Use copyToRealmOrUpdate() to insert or update objects in your local Realm
                            Log.i("Realm","syncObjects $syncedObject ")
                            transactionRealm.copyToRealmOrUpdate(syncedObject)
                        }
                        val user = transactionRealm.where(Users::class.java).equalTo("_id","2").findAll()
                        Log.i("transactionRealm","users details: $user ")*//*
                    }
                }catch (e :Exception){
                    Log.e("transaction","exception $e")
                }
            }else{

            }
            }
        }catch (e:Exception){
            Log.e("login_anonymous","exception $e")
        }
*/


        recyclerView = binding.rvSpecialProducts
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val cardDataList = listOf(
            CardData(R.drawable.arecapalm, "Arecapalm","₹160","50%","₹80"),
            CardData(R.drawable.christmustree, "Christmustree","₹160","50%","₹80"),
            CardData(R.drawable.dianthus, "Dianthus","₹160","50%","₹80"),
            CardData(R.drawable.money_plant, "Money_plant","₹160","50%","₹80"),
            CardData(R.drawable.parijat, "Parijat","₹160","50%","₹80"),
        )
        cardAdapter = CardAdapter(requireContext(), cardDataList)
        recyclerView.adapter = cardAdapter


        recyclerView2 = binding.rvBestProducts
//        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val plantDataList = listOf(
            PlantSellData(R.drawable.p1, "English Ivy","₹80"),
            PlantSellData(R.drawable.jadeplant, "Jade Plant","₹80"),
            PlantSellData(R.drawable.plant1, "Snake Plant","₹80"),
            PlantSellData(R.drawable.p3, "Pothos","₹80"),
            PlantSellData(R.drawable.p4, "Boston Fern","₹80"),
            PlantSellData(R.drawable.p5, "Aloe Vera","₹80"),
            PlantSellData(R.drawable.p6, "Spider Plant","₹80")
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