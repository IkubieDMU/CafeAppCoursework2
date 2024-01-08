package com.example.cafeappcoursework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeappcoursework.model.FavoriteItem

class FavoritesAdapter(private val context: Context, private val favoriteItems: List<FavoriteItem>) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.favProductNameTextView)
        val itemType: TextView = view.findViewById(R.id.favProductTypeTextView)
        val itemPrice: TextView = view.findViewById(R.id.favProductPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorites_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favoriteItems[position]
        holder.itemName.text = item.productName
        holder.itemType.text = item.type
        holder.itemPrice.text = "${item.price} GBP"
        // ... bind other views
    }

    override fun getItemCount(): Int {
        return favoriteItems.size
    }
}
