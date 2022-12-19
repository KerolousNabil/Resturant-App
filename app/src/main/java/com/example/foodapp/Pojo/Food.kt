package com.example.foodapp.Pojo

import java.io.Serializable

data class Food(val title:String,val pic:String,val description:String ,val fee:Double
                ,val star:Int, val time:Int,val calories:Int ,  var numberInCard:Int? = null)
    :Serializable{}