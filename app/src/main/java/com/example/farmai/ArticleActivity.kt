package com.example.farmai

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ArticleActivity : AppCompatActivity() {
  //  @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleTitle = intent.getStringExtra("articleTitle")
        val articleContent = intent.getStringExtra("articleContent")
        val imgg = intent.getStringExtra("articleImg")
        val test = findViewById<TextView>(R.id.text1!!)
        test.setText(articleTitle)
        val testt = findViewById<TextView>(R.id.text2!!)
        testt.setText(articleContent)
        val x = findViewById<ImageView>(R.id.imgview)

      if(imgg == "img1") {
          x.setBackgroundResource(R.drawable.img1)
      }
      else if(imgg == "img2"){
          x.setBackgroundResource(R.drawable.img2)
      }
      else if(imgg == "img3"){
          x.setBackgroundResource(R.drawable.img3)
      }
      else {
          x.setBackgroundResource(R.drawable.img4)
      }

  }
}