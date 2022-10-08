package com.example.rekukletaxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_detoil.*
import kotlinx.android.synthetic.main.list.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {



    fun profilus(v:View){
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var imageList =  listOf<recooc>()

        imageList=listOf<recooc>(
            recooc(
                R.drawable.semerka,
                "Супер эконом",
                "70 руб. + 5 руб/км",
                "Поездка на ВАЗ 2107 - если вы никуда не торопитесь и вам нет дела до комфортабельности в автомобиле, а ситуация в вашем кошельке оставляет желать лучшего, то этот вариант просто создан для вас"
            ,70,5),
            recooc(
                R.drawable.camry,
                "Эконом",
                "140 руб. + 10 руб/км",
                "Поездка на Toyota Camry - средний вариант по средней цене со среднем уровнем комфорта со средней скоростью для среднего человека. Просто и со вкусом."
            ,140,10),
            recooc(
                R.drawable.gazel,
                "Микроавтобус",
                "200 руб. + 15 руб/км",
                "Поездка на ГАЗ-3221 - если вы вместе с большой компанией друзей (ах да, у вас нет друзей) решили куда-то поехать или нужно срочно эвакуировать набуханную биомассу после вписки, то смело выбирайте этот вариант."
            ,200,15),
            recooc(
                 R.drawable.bmw,
                "Бизнес",
                "250 руб. + 20 руб/км",
                "Поездка на BMW M5 F90 - хотите понтануться перед чувачками с района или любимой девкой? Данный вариант полностью удовлетворит вашу потребность."
            ,250,20),
            recooc(
                R.drawable.maybach,
                "Премьер",
                "800 руб. + 50 руб/км",
                "Mercedes-Maybach S-Класс - для тех, кто получил повышение на работе и хочет почувствовать себя чуть ближе к высшему обществу."
            ,800,50),
            recooc(
                R.drawable.lambo,
                "Спорт",
                "1200 руб. + 60 руб/км",
                "Поездка на Lamborghini Huracan - если вы любите чувство скорости, любите покататься с ветерком или просто опаздываете на работу, то скорее заказывайте данный вариант"
            ,1200,60),
            recooc(
                R.drawable.rolls,
                "Элитный",
                "1500 руб. + 80 руб/км",
                "Поездка на Rolls Royce Phantom - если у вас кончается место в кошельке или вы устали считать нули на вашем банковском счету, то это отличный способ исправить данную проблему."

            ,1500,80),
            recooc(
                R.drawable.belaz,
                "Супермегагипермонстр",
                "10000 руб. + 500 руб/км",
                "Поездка на БЕЛАЗЕ - для тех, кто считает, что размер - имеет значение. Если вам нужно перевезти 750 тонн серебряных ложек (почему ложек? Не знаю) или вам нужно с пафосом завалиться на стрелку, ведь при виде вашего транспортного средства все сразу поймут, что с вами связываться не стоит, то заказывайте данный вариант БЕЗ РАЗДУМИЙ!"

            ,10000,500)


        )


        val recyclerView = findViewById<RecyclerView>(R.id.rucuc)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = odop(this, imageList)
        {
            val intent = Intent(this, detoil::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)

        }


    }

}

