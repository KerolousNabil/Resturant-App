package com.example.foodapp.Ui.card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adapters.CardListAdapters
import com.example.foodapp.Helper.ChangeNumberItemListner
import com.example.foodapp.Helper.ManegmentCard
import com.example.foodapp.R
import com.example.foodapp.Ui.main.MainActivity
import com.example.foodapp.databinding.ActivityCardBinding

class CardActivity : AppCompatActivity() {
    private  var tax:Double = 0.0
    private lateinit var manegmentCard: ManegmentCard
    private lateinit var root: ActivityCardBinding
    private lateinit var cardListAdapters: CardListAdapters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = DataBindingUtil.setContentView(this, R.layout.activity_card)
        window.statusBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        window.navigationBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        supportActionBar!!.hide()
        manegmentCard = ManegmentCard(this)
              val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
              root.recyclerCards.layoutManager = linearLayoutManager
                 cardListAdapters = CardListAdapters(manegmentCard.getlistCard(),this,object :ChangeNumberItemListner{
                     override fun changed() {
                         calculateCard()
                     }

                 })
        calculateCard()

        root.recyclerCards.adapter = cardListAdapters

        if (manegmentCard.getlistCard().isEmpty())
        {
            root.textemptycard.visibility = View.VISIBLE
            root.scrollview.visibility = View.GONE
        }
        else
        {
            root.textemptycard.visibility = View.GONE
            root.scrollview.visibility = View.VISIBLE
        }

        root.homeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        root.cardBtn.setOnClickListener {
            startActivity(Intent(this,CardActivity::class.java))

        }

    }
    fun calculateCard()
    {
        val percenttax = 0.14
        val delevery = 10.0
        tax = Math.round((manegmentCard.getTotalPrice() * percenttax)*100.0)/100.0
        val total = Math.round((manegmentCard.getTotalPrice() +tax* delevery)*100.0)/100.0
        val itemtotal = Math.round(manegmentCard.getTotalPrice()*100.0)/100.0
        root.itemtotaltext.text = "$" + itemtotal
        root.taxtext.text = "$"+ tax
        root.deliverytext.text = "$"+delevery
        root.totaltext.text = "$" +total
    }
}