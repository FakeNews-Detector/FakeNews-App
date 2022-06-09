package com.bangkit.capstone.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstone.R
import com.bumptech.glide.Glide

class RecyclerAdapter(
    private var titles: List<String>,
    private var description: List<String>,
    private var images: List<String>
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val itemDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val itemImage: ImageView = itemView.findViewById(R.id.imageView)

        //takes care of click events
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDescription.text = description[position]

        Glide.with(holder.itemImage)
            .load(images[position])
            .into(holder.itemImage)
    }
}