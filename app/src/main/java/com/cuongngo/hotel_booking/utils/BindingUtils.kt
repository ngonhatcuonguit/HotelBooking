package com.cuongngo.hotel_booking.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.response.HotelModel

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

@BindingAdapter("hotelPrice")
fun setHotelPrice(view: TextView, price: Long?) {
    price?.let {
        view.text = view.context.resources.getString(R.string.hotel_price, it.formatToPrice())
    }
}

@BindingAdapter("loadImageHotel")
fun loadImageHotel(view: ImageView, hotelModel: HotelModel) {
    val url = hotelModel.images?.firstOrNull()?.medium.orEmpty()
    Glide.with(view)
        .load(url)
        .placeholder(MyDrawableCompat.createProgressDrawable(view.context))
        .apply(RequestOptions.centerCropTransform())
        .error(R.drawable.background)
        .into((view))
}

@BindingAdapter("showLocation")
fun showLocation(view: TextView, hotelModel: HotelModel){
    val location = "${hotelModel.ward?.name.toString()} + ${hotelModel.district?.name.toString()} + ${hotelModel.city?.desc.toString()}"
    view.text = location
}


