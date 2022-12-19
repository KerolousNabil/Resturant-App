package com.example.foodapp.Helper

import android.content.Context
import android.widget.Toast
import com.example.foodapp.Pojo.Food

class ManegmentCard {
    private val mycnt: Context
    private val tinyDB:TinyDB

    constructor(mycnt: Context)
    {
        this.mycnt = mycnt
        this.tinyDB = TinyDB(mycnt)
    }
    fun insertFood(item:Food)
    {
        val listfood:ArrayList<Food> =getlistCard()
        var excistAlready:Boolean = false
        var n=0
        for (i in 0 until listfood.size)
        {
            if (listfood.get(i).title.equals(item.title))
            {
                excistAlready = true
                n=i
            }
        }
        if (excistAlready) { listfood.get(n).numberInCard = item.numberInCard }
        else
        { listfood.add(item) }
        tinyDB.putListObject("CardList",listfood)
        Toast.makeText(mycnt,"Added to your card",Toast.LENGTH_LONG).show()
    }
    fun getlistCard():ArrayList<Food>
    {
        return tinyDB.getListObject("CardList")
    }
    fun minusNumberFood(listfood:ArrayList<Food> , position:Int , changeNumberItemListner: ChangeNumberItemListner )
    {
        if (listfood.get(position).numberInCard==1)
        {
            listfood.removeAt(position)
        }
        else
        {
            listfood.get(position).numberInCard = listfood.get(position).numberInCard?.minus(1)
        }

        tinyDB.putListObject("CardList",listfood)
        changeNumberItemListner.changed()

    }

    fun plusNumberFood(listfood:ArrayList<Food> , position:Int , changeNumberItemListner: ChangeNumberItemListner)
    {
        listfood.get(position).numberInCard = listfood.get(position).numberInCard?.plus(1)
        tinyDB.putListObject("CardList",listfood)
        changeNumberItemListner.changed()
    }
    fun getTotalPrice():Double
    {
        val listfood:ArrayList<Food> =getlistCard()
        var price:Double = 0.0
        for ( i in 0 until listfood.size)
        {
            price += (listfood.get(i).fee * listfood.get(i).numberInCard!!)
        }
        return price
    }

}