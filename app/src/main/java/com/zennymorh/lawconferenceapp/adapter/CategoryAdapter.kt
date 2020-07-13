package com.zennymorh.lawconferenceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.lawconferenceapp.R
import com.zennymorh.lawconferenceapp.models.Category

class CategoryAdapter(private var categoryList:ArrayList<Category>, var context: Context):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categoryList[position]
        holder.bind(category)
    }

    inner class CategoryViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(
            R.layout.category_item, parent,
            false)) {
        private var imageId: ImageView? = null
        private var names: TextView? = null
        private var container: CardView? = null

        init {
            imageId = itemView.findViewById(R.id.categoryIcon)
            names = itemView.findViewById(R.id.categoryName)
            container = itemView.findViewById(R.id.container)
        }
        fun bind(category: Category){
            names?.text = category.name
            imageId?.setImageResource(category.image)
            container?.setCardBackgroundColor(ContextCompat.getColor(context, category.backgroundColor))
        }

    }
}