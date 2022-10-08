package com.example.rekukletaxi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class recooc (
    val src: Int,
    val title: String,
    val price:String,
    val desk: String,
    val priceint:Int,
    val tarifint:Int
):Parcelable