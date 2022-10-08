package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_regist.*

class regist : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)

        registbut.setOnClickListener{
            signUpUser()
        }
        auth = FirebaseAuth.getInstance()

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
    private fun signUpUser(){
        progressBar2.visibility= View.VISIBLE
        if ((editText.text.toString()!="")&&(vostpost.text.toString()!="")&&(editText3.text.toString()!="")&&(editText5.text.toString()!="")&&(fonum.text.toString().length==17)) {
            if (editText3.text.toString() == editText5.text.toString()) {
                auth.createUserWithEmailAndPassword(
                    vostpost.text.toString(),
                    editText3.text.toString()
                ).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Пользователь зарегистрирован",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        var databaza: DatabaseReference = FirebaseDatabase.getInstance().getReference()
                        var id = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
                        databaza.child(id!!).child("name").setValue(editText.text.toString())
                        // Firebase.database.getReference("name").setValue(editText.text.toString())
                        databaza.child(id!!).child("email").setValue(vostpost.text.toString())
                        databaza.child(id!!).child("phone").setValue(fonum.text.toString())

                        //Firebase.database.getReference("email").setValue(editText2.text.toString())
                        progressBar2.visibility= View.GONE
                        val intent = Intent(this,profile::class.java)
                        startActivity(intent)


                    } else {
                        Toast.makeText(baseContext, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                        Log.i("signUpUser", task.exception.toString())
                        progressBar2.visibility= View.GONE
                    }
                }
            } else {
                Toast.makeText(baseContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                progressBar2.visibility= View.GONE
            }
        }
        else{
            Toast.makeText(baseContext, "Чего то не хватает", Toast.LENGTH_SHORT).show()
            progressBar2.visibility= View.GONE
        }
    }
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun profilus(v: View){
        val intent = Intent(this,vibor::class.java)
        startActivity(intent)
    }
}