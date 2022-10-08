package com.example.rekukletaxi


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_oform.*
import kotlinx.android.synthetic.main.activity_oform.textView10
import kotlinx.android.synthetic.main.activity_oform.textView4
import kotlinx.android.synthetic.main.activity_oform.textView8
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class oform : AppCompatActivity() {


    lateinit var arguments:Bundle
    var price:Int = 0
    var tartit:String = ""
    var otpr:String = ""
    var nazn:String = ""

/*    override fun onAttach(context: Context?) {
        appExecutors = AppExecutors()
        super.onAttach(context)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oform)


        arguments = intent.extras!!
        price = arguments!!["price"].toString().toInt()
        tartit = arguments!!["title"].toString()
        otpr = arguments!!["otpr"].toString()
        nazn = arguments!!["nazn"].toString()

        textView16.text = tartit
        textView4.text = otpr
        textView6.text = nazn
        textView8.text = price.toString()+" рублей"
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
                    textView10.text = value.toString()
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
                    textView12.text = value.toString()
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
                    textView14.text = value.toString()
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
    fun buy(v: View){
       /*     appExecutors.diskIO().execute {
                val props = System.getProperties()
                props.put("mail.smtp.host", "smtp.gmail.com")
                props.put("mail.smtp.socketFactory.port", "465")
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                props.put("mail.smtp.auth", "true")
                props.put("mail.smtp.port", "465")

                val session =  Session.getInstance(props,
                    object : javax.mail.Authenticator() {
                        //Authenticating the password
                        override fun getPasswordAuthentication(): PasswordAuthentication {
                            return PasswordAuthentication(Credentials.EMAIL, Credentials.PASSWORD)
                        }
                    })

                try {
                    //Creating MimeMessage object
                    val mm = MimeMessage(session)
                    val emailId = emailEditText.text.toString()
                    //Setting sender address
                    mm.setFrom(InternetAddress(Bitmap.Config.EMAIL))
                    //Adding receiver
                    mm.addRecipient(
                        Message.RecipientType.TO,
                        InternetAddress(emailId))
                    //Adding subject
                    mm.subject = "Your mail's subject."
                    //Adding message
                    mm.setText("Your mail body.")

                    //Sending email
                    Transport.send(mm)

                    appExecutors.mainThread().execute {
                        //Something that should be executed on main thread.
                    }

                } catch (e: MessagingException) {
                    e.printStackTrace()
                }
            }*/

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



}