package com.ensar.tmdbkotlin.utils


import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import android.net.ConnectivityManager
import java.text.NumberFormat
import java.util.*


@BindingAdapter("android:imageUrl")
fun ImageView.setImageUrl(imagePath: Int) {
    if (imagePath == 0) return
    Picasso.get()
            .load(imagePath)
            .into(this)
}

fun Activity.isNetworkAvailable() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun String.toMoneyFormat() : String{
    return NumberFormat.getCurrencyInstance(Locale.US).format(this.toDouble())
}