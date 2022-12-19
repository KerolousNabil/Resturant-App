package com.example.foodapp.Helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.Pojo.Food
import com.example.foodapp.R

class CardListAdapters : RecyclerView.Adapter<CardListAdapters.MyViewHolder>{
    private lateinit var foodlistselected : ArrayList<Food>
    private lateinit var manegmentCard:ManegmentCard
    private lateinit var changeNumberItemListner: ChangeNumberItemListner


    constructor(foodlistselected : ArrayList<Food> , mycontext : Context ,changeNumberItemListner: ChangeNumberItemListner){
        this.foodlistselected = foodlistselected
        manegmentCard = ManegmentCard(mycontext)
        this.changeNumberItemListner = changeNumberItemListner

    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

          val titleFood: TextView = itemView.findViewById(R.id.foodtitle)
          val imageFood : ImageView = itemView.findViewById(R.id.imagefoodselected)
          val priceEachItem: TextView = itemView.findViewById(R.id.priceEachItem)
          val minusItem: ImageView = itemView.findViewById(R.id.minusItembtn)
          val plusItem: ImageView = itemView.findViewById(R.id.plusItembtn)
          val totalEachItem : TextView = itemView.findViewById(R.id.totaleachItem)
          val numCount : TextView = itemView.findViewById(R.id.numItemText)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item_selected_layout,parent , false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val food : Food = foodlistselected[position]
        holder.titleFood.text = food.title
        holder.priceEachItem.text = "$"+food.fee
        holder.totalEachItem.text = "$"+ Math.round((foodlistselected[position].numberInCard!! *foodlistselected[position].fee))
        holder.numCount.text = java.lang.String.valueOf(foodlistselected[position].numberInCard)
        val  drawableResource:Int =holder.itemView.context.resources.getIdentifier(food.pic,"drawable",holder.itemView.context.packageName)
        Glide.with(holder.itemView.context).load(drawableResource).into(holder.imageFood)

        holder.plusItem.setOnClickListener {
            manegmentCard.plusNumberFood(foodlistselected,position , object : ChangeNumberItemListner{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemListner.changed()
                }

            })
        }
        holder.minusItem.setOnClickListener {
            manegmentCard.minusNumberFood(foodlistselected,position , object : ChangeNumberItemListner{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemListner.changed()
                }

            })
        }
    }

    override fun getItemCount(): Int {
       return foodlistselected.size
    }
}