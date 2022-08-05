package com.cuongngo.hotel_booking.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .into((view))
}

@BindingAdapter("showRating")
fun showRating(textView: TextView, rating: Float){
    textView.text = rating.toString()
}

@BindingAdapter("displayPrice")
fun displayPrice(textView: TextView, price: Long){
    textView.text = "$$price"
}



