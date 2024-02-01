package com.example.farmai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.farmai.R

class SingInSingnUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sing_in_singn_up)

        val singin = findViewById<Button>(R.id.signin1)
        val signup = findViewById<Button>(R.id.signup1)

        singin.setOnClickListener{
            val inten = Intent(this, signinActivity::class.java)
            startActivity(inten)
        }
        signup.setOnClickListener {
            val inten = Intent(this, MainActivity2::class.java)
            startActivity(inten)
        }
    }
}