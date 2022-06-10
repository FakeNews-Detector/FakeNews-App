package com.bangkit.capstone.data.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.capstone.data.api.NewsApiJSONItem
import com.bangkit.capstone.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide

class RecyclerAdapter(private val results: ArrayList<NewsApiJSONItem>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val TAG = "MainAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val newsBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(newsBinding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        Glide.with(holder.itemView)
            .load(result.gambar)
            .into(holder.itemLayoutBinding.imageViews)
        holder.itemLayoutBinding.tvTitle.text = result.judul
        holder.itemLayoutBinding.tvDescription.text = result.deskripsi
        holder.itemLayoutBinding.itemCardView.setOnClickListener {
            listener.onClick(result)
        }
        Log.d(TAG, result.gambar)
    }

    class ViewHolder (val itemLayoutBinding: ItemLayoutBinding): RecyclerView.ViewHolder(itemLayoutBinding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<NewsApiJSONItem>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick( result: NewsApiJSONItem)
    }
}
