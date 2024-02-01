package com.example.farmai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.farmai.R
import com.google.firebase.auth.ktx.auth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.Firebase


class signinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val bt2 = findViewById<Button>(R.id.signip)
        bt2.setOnClickListener{
            val email = findViewById<EditText>(R.id.emailid).text.toString()
            val pass = findViewById<EditText>(R.id.password).text.toString()
            signin(email,pass)
        }

    }

    private fun signin(email : String , password : String){

        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val inten1  = Intent(this, MainActivity::class.java)
                    startActivity(inten1)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }



}