package com.example.foodapp.Ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.foodapp.R
import com.example.foodapp.Ui.main.MainActivity
import com.example.foodapp.databinding.ActivityIntroBinding
class IntroActivity : AppCompatActivity() {
    private lateinit var root: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        root = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        window.statusBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        window.navigationBarColor = this.resources.getColor(android.R.color.holo_orange_light)
        supportActionBar!!.hide()
        root.getStarted.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}