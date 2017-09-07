package com.example.diego.dependencyinjectiondemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FavoriteItemAdapter(private val items: List<FavoriteItem>) : RecyclerView.Adapter<FavoriteItemViewHolder>() {

    override fun onBindViewHolder(holder: FavoriteItemViewHolder, position: Int) {
        val item = items[position]
        holder.affinity.text = item.name
        holder.strength.text = item.attachment.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteItemViewHolder(rootView)
    }

    override fun getItemCount(): Int = items.size

}

class FavoriteItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    val affinity: TextView = rootView.findViewById<TextView>(R.id.content_text_view)
    val strength: TextView = rootView.findViewById<TextView>(R.id.strength_text_view)
}