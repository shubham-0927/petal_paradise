package com.example.plants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.R
import com.example.plants.data.CardData

class BonsaiCardAdapter(private val context: Context, private val cardDataList: List<CardData>) :
    RecyclerView.Adapter<BonsaiCardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_ad)
        val textView: TextView = itemView.findViewById(R.id.tv_ad_name)
        val price: TextView = itemView.findViewById(R.id.tv_ad_price)
        val discount_price: TextView = itemView.findViewById(R.id.tv_dis_price)
        val percent_discount: TextView = itemView.findViewById(R.id.tv_discount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.special_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardData = cardDataList[position]

        holder.imageView.setImageResource(cardData.imageResourceId)
        holder.textView.text = cardData.text
        holder.price.text = cardData.price
        holder.percent_discount.text = cardData.dis
        holder.discount_price.text = cardData.disPrice
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }
}