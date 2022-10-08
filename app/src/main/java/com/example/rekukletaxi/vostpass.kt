package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_vostpass.*

class vostpass : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vostpass)
        auth = FirebaseAuth.getInstance()
        vost.setOnClickListener {
            recovery()
        }
    }
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
    override fun onStart() {
        super.onStart()
        var user  = FirebaseAuth.getInstance().currentUser

        if (user!=null) {
            Toast.makeText(baseContext, "Невозможно выполнить действие", Toast.LENGTH_SHORT)
                .show()
            // User is signed in.
            val intent = Intent(this,profile::class.java)
            startActivity(intent)
        }
    }
    private fun recovery(){
        if (vostpost.text.toString() != ""){
            val email = vostpost.text.toString().trim()

            auth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@vostpass, "Письмо с ссылкой для востановления пароля отправлено вам на почту", Toast.LENGTH_LONG).show()
                        val intent = Intent(this,login::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@vostpass, "Не удалось отправить письмо для восстановления пароля", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else{
            Toast.makeText(this, "Заполните поле Почта ", Toast.LENGTH_SHORT).show()
        }
    }
}