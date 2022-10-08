package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_changedata.*

class changedata : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var user  = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changedata)

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
                    editText.setText(value.toString())
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
                    vostpost.setText(value.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("loshara", "Failed to read value.", error.toException())
                }
            })

        auth = FirebaseAuth.getInstance()
    }
    fun katalog2(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun profilus(v: View){

        val intent = Intent(this,profile::class.java)
        startActivity(intent)

    }
    fun chungus(v: View){
        if ((editText.text.toString()!="")&&(vostpost.text.toString()!="")&&(editText3.text.toString()!="")&&(editText5.text.toString()!="")&&(fonum.text.toString().length==17)) {
            if (editText3.text.toString() == editText5.text.toString()) {
                user?.updateEmail(vostpost.text.toString())?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Данные успешно изменены",
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
                        val intent = Intent(this,profile::class.java)
                        startActivity(intent)


                    } else {
                        Toast.makeText(baseContext, task.exception?.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(baseContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(baseContext, "Чего то не хватает", Toast.LENGTH_SHORT).show()
        }
    }
}