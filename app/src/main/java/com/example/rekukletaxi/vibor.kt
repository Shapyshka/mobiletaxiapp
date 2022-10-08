package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class vibor : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        var user  = FirebaseAuth.getInstance().currentUser

        if (user!=null) {
            // User is signed in.
            Toast.makeText(baseContext, "Невозможно выполнить действие", Toast.LENGTH_SHORT)
                    .show()
            /*val intent = Intent(this,profile::class.java)
            startActivity(intent)*/

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibor)
    }
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun vhodus(v: View){
        val intent = Intent(this,login::class.java)
        startActivity(intent)
    }
    fun regus(v: View){
        val intent = Intent(this,regist::class.java)
        startActivity(intent)
    }
}