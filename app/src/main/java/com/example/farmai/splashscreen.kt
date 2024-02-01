package com.example.farmai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    public override fun onStart() {
        super.onStart()
        val auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null){
            Handler().postDelayed({
                val inten = Intent(this, MainActivity::class.java)
                startActivity(inten)
                finish()
            },1500)
        }
        else{
            Handler().postDelayed({
                val inten = Intent(this, SingInSingnUpActivity::class.java)
                startActivity(inten)
                finish()
            },1500)
        }
    }




}