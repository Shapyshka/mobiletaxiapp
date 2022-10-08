package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*

class profile : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        var user  = FirebaseAuth.getInstance().currentUser

        if (user==null) {
            // User is signed in.
            Toast.makeText(baseContext, "Невозможно выполнить действие", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this,vibor::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


       button9.setOnClickListener{
            val intent = Intent(this,changedata::class.java)

            startActivity(intent)
        }


        var databaza: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        var id = FirebaseAuth.getInstance().getCurrentUser()?.getUid()
        //Firebase.database.getReference(id!!)
        databaza.child(id!!).child("name")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<String>()
                    Log.d("harosh", "Value is: $value")
                    textView5.text = value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("loshara", "Failed to read value.", error.toException())
                }
            })
        //Firebase.database.getReference("email")
        databaza.child(id!!).child("email")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<String>()
                    Log.d("harosh", "Value is: $value")
                    textView4.text = value.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("loshara", "Failed to read value.", error.toException())
                }
            })
        databaza.child(id!!).child("phone")
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue<String>()
                    Log.d("harosh", "Value is: $value")
                    textView10.text = value.toString()
                    progressBar.visibility= View.GONE
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("loshara", "Failed to read value.", error.toException())
                }
            })
    }
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun signout(v: View){

        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this,vibor::class.java)
        startActivity(intent)
    }

}