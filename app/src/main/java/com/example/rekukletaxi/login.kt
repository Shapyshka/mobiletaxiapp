package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        var user  = FirebaseAuth.getInstance().currentUser

        if (user!=null) {
            // User is signed in.
            Toast.makeText(baseContext, "Невозможно выполнить действие", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this,profile::class.java)
            startActivity(intent)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        registbut.setOnClickListener{
            signInUser()
        }
    }
    private fun signInUser(){
        progressBar3.visibility= View.VISIBLE
        if ((vostpost.text.toString()!="")&&(editText3.text.toString()!="")) {
            auth.signInWithEmailAndPassword(vostpost.text.toString(), editText3.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "Пользователь авторизован", Toast.LENGTH_LONG)
                            .show()
                        val intent = Intent(this,profile::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Неверно введены данные", Toast.LENGTH_LONG).show()
                    }
                }

        }
        else{
            Toast.makeText(baseContext, "Чего-то не хватает!", Toast.LENGTH_SHORT).show()
        }
        progressBar3.visibility= View.GONE
    }
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun vostus(v: View){
        val intent = Intent(this,vostpass::class.java)
        startActivity(intent)
    }

    fun profilus(v: View){
        val intent = Intent(this,vibor::class.java)
        startActivity(intent)
    }
}