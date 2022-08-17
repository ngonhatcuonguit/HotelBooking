package com.cuongngo.hotel_booking.utils

import android.graphics.Color
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
        .placeholder(MyDrawableCompat.createProgressDrawable(view.context))
        .apply(RequestOptions.centerCropTransform())
        .error(R.drawable.background)
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
@BindingAdapter("showDetailFacility")
fun showDetailFacility(view: ImageView, status: Int) {
    if (status == 2){
        view.setColorFilter(Color.parseColor("#7D7D7D"))
    }
}

@BindingAdapter("textColorState")
fun textColorState(view: TextView, status: Int) {
    if (status == 2) {
        view.setTextColor(Color.parseColor("#7D7D7D"))
    }else {
        view.setTextColor(Color.parseColor("#000000"))
    }
}

@BindingAdapter("showSqft")
fun showSqft(view: TextView, sqft: Int) {
    view.text = sqft.toString() + "sqft"
}

@BindingAdapter("showGuest")
fun showGuest(view: TextView, gest: Int) {
    view.text = gest.toString() + " Night"
}

@BindingAdapter("loadImageHotel")
fun loadImageHotel(view: ImageView, hotelModel: HotelModel?) {
    val url = hotelModel?.images?.firstOrNull()?.medium.orEmpty()
    Glide.with(view)
        .load(url)
        .placeholder(MyDrawableCompat.createProgressDrawable(view.context))
        .apply(RequestOptions.centerCropTransform())
        .error(R.drawable.background)
        .into((view))
}

@BindingAdapter("showLocation")
fun showLocation(view: TextView, hotelModel: HotelModel?){
    val location = "${hotelModel?.ward?.name.toString()}, ${hotelModel?.district?.name.orEmpty()}, ${hotelModel?.city?.desc.toString()}"
    view.text = location
}


