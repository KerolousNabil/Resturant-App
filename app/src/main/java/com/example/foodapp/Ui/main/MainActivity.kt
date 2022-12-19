package com.example.foodapp.Ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Pojo.CategoriesAdapters
import com.example.foodapp.Pojo.Categories
import com.example.foodapp.Pojo.Food
import com.example.foodapp.Pojo.FoodAdapters
import com.example.foodapp.R
import com.example.foodapp.Ui.card.CardActivity
import com.example.foodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var root: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = DataBindingUtil.setContentView(this, R.layout.activity_main)

        window.statusBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        window.navigationBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        supportActionBar!!.hide()
        recyclerCategories()
        recyclerRecommanded()
        root.homeBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        root.cardBtn.setOnClickListener {
            startActivity(Intent(this,CardActivity::class.java))

        }
    }

     private  fun recyclerCategories()
     {
         var linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
         root.recyclerCategories.layoutManager = linearLayoutManager
         val categorieslist :ArrayList<Categories> = ArrayList<Categories>()
         categorieslist.add(Categories("Pizza", "cat_1"))
         categorieslist.add(Categories("Burger", "cat_2"))
         categorieslist.add(Categories("Hotdog", "cat_3"))
         categorieslist.add(Categories("Drinks", "cat_4"))
         categorieslist.add(Categories("Donut", "cat_5"))
         var adapter = CategoriesAdapters(this,categorieslist)
         root.recyclerCategories.adapter = adapter

     }
    private fun recyclerRecommanded()
    {
        var linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        root.recyclerPopular.layoutManager = linearLayoutManager
        val foodlist :ArrayList<Food> = ArrayList<Food>()
        foodlist.add(Food("Pepprone pizza","pizza1", "slice prepprone,mozzarella cheese,fresh orange , ground black papper , pizza sauce " , 13.0,5,20,1250))
        foodlist.add(Food("Cheese burger","burger", "beef, Gouda cheese , Special sauce , Lettuce , Tomato " , 15.5,3,15,1500))
        foodlist.add(Food("Vegetables pizza","pizza3", "Olive oil , Vegetables oil , Pitted Kalamata , cherry tomatoes , fresh orange , basil " , 20.0,4,11,2500))
        var adapter = FoodAdapters(this,foodlist)
        root.recyclerPopular.adapter = adapter
    }
}