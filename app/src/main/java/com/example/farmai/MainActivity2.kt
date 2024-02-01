package com.example.farmai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.farmai.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btn = findViewById<Button>(R.id.signup)
        btn.setOnClickListener {
                val email = findViewById<EditText>(R.id.emailid).text.toString()
            val pass = findViewById<EditText>(R.id.password).text.toString()
             val city = findViewById<EditText>(R.id.city).text.toString()
            val name  = findViewById<EditText>(R.id.nameid).text.toString()
            signup(name,email,pass,city)
        }

    }



  private fun signup(name : String,email : String , password : String,city : String){
      val auth = Firebase.auth
      auth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener(this) { task ->
              if (task.isSuccessful) {
                  // Sign in success, update UI with the signed-in user's information
                  val user = auth.currentUser
                  val inten1  = Intent(this, MainActivity3::class.java)
                  startActivity(inten1)
                  val uid = user?.uid.toString()
          //        addusertodatabse(name,email,uid,city,upi_id)
//                  updateUI(user)

              } else {
                  // If sign in fails, display a message to the user.
                  Log.w("app", "createUserWithEmail:failure", task.exception)
                  Toast.makeText(baseContext, "Authentication failed.",
                      Toast.LENGTH_SHORT).show()
//                  updateUI(null)
              }
          }
      val inten1  = Intent(this, MainActivity::class.java)
      startActivity(inten1)
  }


}