package com.example.rekukletaxi

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class odop (

    private val context: Context,
    private val images: List<recooc>,

    val listener: (recooc)->Unit): RecyclerView.Adapter<odop.ImageViewholder>(){
    class ImageViewholder(view: View):RecyclerView.ViewHolder(view){
        val imageSrc = view.findViewById<ImageView>(R.id._image)
        val price = view.findViewById<TextView>(R.id.pricelist)
        val title = view.findViewById<TextView>(R.id._title)

        fun bindView (image: recooc, listener: (recooc) -> Unit){


            imageSrc.setImageResource(image.src)
            title.text = image.title
            price.text = image.price
            itemView.setOnClickListener{listener(image)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ImageViewholder = ImageViewholder(LayoutInflater.from(context).inflate(R.layout.list,parent,false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewholder, position: Int) {




        holder.bindView(images[position],listener)
    }

}
