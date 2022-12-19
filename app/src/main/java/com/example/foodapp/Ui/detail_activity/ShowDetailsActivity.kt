package com.example.foodapp.Ui.detail_activity

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.foodapp.Helper.ManegmentCard
import com.example.foodapp.Pojo.Food
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityShowDetailsBinding


class ShowDetailsActivity : AppCompatActivity() {
    private lateinit var rootBinding: ActivityShowDetailsBinding
    private lateinit var food: Food
    private lateinit var manegmentCard: ManegmentCard
    var animation: Animation? = null

    var numberOrder = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = DataBindingUtil.setContentView( this@ShowDetailsActivity, R.layout.activity_show_details)
          supportActionBar!!.hide()
        window.statusBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        window.navigationBarColor = this.resources.getColor(android.R.color.holo_orange_light)
          manegmentCard = ManegmentCard(this)
       getBungle()

    }
  fun getBungle() {

     food = getIntent().getSerializableExtra("object") as Food
     var drawableResourceId=this.resources.getIdentifier(food.pic,"drawable",this.packageName)
     Glide.with(this).load(drawableResourceId).into(rootBinding.foodPc)

     animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
     rootBinding.foodPc.animation = animation
     rootBinding.titleText.text = food.title
     rootBinding.priceText.text = "$"+food.fee
     rootBinding.descriptionText.text = food.description
     rootBinding.numItemText.text = java.lang.String.valueOf(numberOrder)
     rootBinding.caloriesText.text = food.calories.toString()+" calories"
     rootBinding.starTxt.text = food.star.toString()+""
     rootBinding.timeText.text = food.time.toString()+" minutes"
     rootBinding.totalPriceText.text = (numberOrder * food.fee).toString()

     rootBinding.plusItembtn.setOnClickListener {
         numberOrder+=1
         rootBinding.numItemText.text = java.lang.String.valueOf(numberOrder)
         rootBinding.totalPriceText.text = (numberOrder * food.fee).toString()

     }

     rootBinding.minusItembtn.setOnClickListener {
         if (numberOrder >1){
             numberOrder -= 1
         }

         rootBinding.numItemText.text = java.lang.String.valueOf(numberOrder)
         rootBinding.totalPriceText.text = (numberOrder * food.fee).toString()
     }
     rootBinding.addedTOCardBtn.setOnClickListener {
         val pdialog = ProgressDialog(this)
         pdialog.setTitle("Your Card")
         pdialog.setMessage("This item has been added successfully")
         pdialog.show()
         val progressRunnable = Runnable { pdialog.cancel() }

         val pdCanceller = Handler()
         pdCanceller.postDelayed(progressRunnable, 2000)
         food.numberInCard = numberOrder
         manegmentCard.insertFood(food)
     }
 }
}