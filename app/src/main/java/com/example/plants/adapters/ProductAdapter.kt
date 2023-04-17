package com.example.plants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.R
import com.example.plants.data.CardData
import com.example.plants.data.PlantSellData
import com.example.plants.fragments.categories.ProductDetailsFragment
import com.example.plants.fragments.shopping.PlantDetailsFragment

class ProductAdapter(private val context: Context, private val cardDataList: List<PlantSellData>, private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_product)
        val plantname: TextView = itemView.findViewById(R.id.plant_name)
        val plantPrice: TextView = itemView.findViewById(R.id.plant_price)

        init {
            // Set click listener on the root view of the card
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = cardDataList[position]
                    // Handle click on the card
                    Toast.makeText(itemView.context, "Clicked: ${clickedItem.name}", Toast.LENGTH_SHORT).show()
               /*     val fragment = ProductDetailsFragment.newInstance(clickedItem.name)
                    fragmentManager.beginTransaction()
                        .replace(R.id.shoppinHostFragment, fragment)
                        .addToBackStack(null)
                        .commit()*/
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.product_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardData = cardDataList[position]

        holder.imageView.setImageResource(cardData.imageResourceId)
        holder.plantname.text = cardData.name
        holder.plantPrice.text = cardData.price
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }
}