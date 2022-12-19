package com.example.foodapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.Pojo.Categories
import com.example.foodapp.R

class CategoriesAdapters(var mycontext : Context, private val categorieslist : ArrayList<Categories>) : RecyclerView.Adapter<CategoriesAdapters.MyViewHolder>() {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

          val name_categories: TextView = itemView.findViewById(R.id.categoriesname)
          val image_categories : ImageView = itemView.findViewById(R.id.imagecategories)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.categories_layout,parent , false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categories : Categories = categorieslist[position]
        holder.name_categories.text = categories.title
        var picURL:String = ""
        when(position){
            0-> picURL= "cat_1"
            1-> picURL= "cat_2"
            2-> picURL= "cat_3"
            3-> picURL= "cat_4"
            4-> picURL= "cat_5"
        }
        val  drawableResource:Int =holder.itemView.context.resources.getIdentifier(picURL,"drawable",holder.itemView.context.packageName)
        Glide.with(holder.itemView.context).load(drawableResource).into(holder.image_categories)
    }

    override fun getItemCount(): Int {
       return categorieslist.size
    }
}