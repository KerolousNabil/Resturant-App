package com.example.foodapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.example.foodapp.Pojo.Food
import com.example.foodapp.R
import com.example.foodapp.Ui.detail_activity.ShowDetailsActivity

class FoodAdapters(var mycontext : Context, private val foodlist : ArrayList<Food>) : RecyclerView.Adapter<FoodAdapters.MyViewHolder>() {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

          val title_food: TextView = itemView.findViewById(R.id.title_food)
          val image_food : ImageView = itemView.findViewById(R.id.image_food)
          val price_food: TextView = itemView.findViewById(R.id.pricefood)
          val addbtn: ImageView = itemView.findViewById(R.id.addBtn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_layout,parent , false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val food : Food = foodlist[position]
        holder.title_food.text = food.title
        holder.price_food.text = "$"+food.fee
        val  drawableResource:Int =holder.itemView.context.resources.getIdentifier(food.pic,"drawable",holder.itemView.context.packageName)
        Glide.with(holder.itemView.context).load(drawableResource).into(holder.image_food)
        holder.addbtn.setOnClickListener {
            var intent =Intent(mycontext,ShowDetailsActivity::class.java)
            intent.putExtra("object",foodlist.get(position))
            mycontext.startActivity(intent)
            Animatoo.animateDiagonal(mycontext)
        }
    }

    override fun getItemCount(): Int {
       return foodlist.size
    }
}