package com.example.cafeappcoursework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeappcoursework.model.Products


class ProductAdapter(private val context: Context, private val productList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productNameTextView)
        val productType: TextView = itemView.findViewById(R.id.productTypeTextView)
        val productPrice: TextView = itemView.findViewById(R.id.productPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_layout, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.productName.text = currentItem.productName
        holder.productType.text = context.getString(R.string.product_type,currentItem.type)
        val formattedPrice = String.format("%.2f", currentItem.price)
        holder.productPrice.text = context.getString(R.string.price_format, formattedPrice)
    }

    override fun getItemCount(): Int {
        return productList.size
    }



}
