package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class detoil : AppCompatActivity() {
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun profilus(v: View){
        var user  = FirebaseAuth.getInstance().currentUser

        if (user!=null) {
            // User is signed in.
            val intent = Intent(this,profile::class.java)
            startActivity(intent)
        } else {
            // No user is signed in.
            val intent = Intent(this,vibor::class.java)
            startActivity(intent)
        }
    }
    fun buy(v: View){
        var user  = FirebaseAuth.getInstance().currentUser

        if (user!=null) {
            // User is signed in.
            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("price",pricus)
            intent.putExtra("tarif",tarifus)
            intent.putExtra("title",tarname)
            startActivity(intent)
        } else {
            // No user is signed in.
            Toast.makeText(applicationContext, "Войдите или зарегистрируйтесь", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,vibor::class.java)
            startActivity(intent)

        }
    }
    var pricus:Int = 0
    var tarifus:Int = 0
    var tarname: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detoil)
        val item = intent.getParcelableExtra<recooc>("OBJECT_INTENT")
        val imgSrc = findViewById<ImageView>(R.id._imageDetail)
        val imgPrice = findViewById<TextView>(R.id.pricedet)
        val imgTitle = findViewById<TextView>(R.id._imageTitle)
        val imgDesc = findViewById<TextView>(R.id._imageDesc)
        imgSrc.setImageResource(item!!.src)
        imgPrice.text = item.price
        imgTitle.text=item.title
        imgDesc.text=item.desk

        pricus = item.priceint
        tarifus = item.tarifint
        tarname = item.title
    }
}