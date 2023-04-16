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

class CardAdapter(private val context: Context, private val cardDataList: List<CardData>) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_ad)
        val textView: TextView = itemView.findViewById(R.id.tv_ad_name)
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
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }
}