package com.cuongngo.hotel_booking.ui.hoteldetail

import android.content.Intent
import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivityHotelDetailBinding
import com.cuongngo.hotel_booking.ui.auth.LoginActivity

class HotelDetailActivity : BaseActivity<ActivityHotelDetailBinding>() {

    override fun inflateLayout() = R.layout.activity_hotel_detail

    override fun setUp() {
        with(binding){
            showMoreTextView.apply {
                setShowingLine(3)
                setShowLessTextColor(App.getResources().getColor(R.color.color_primary))
                setShowMoreTextColor(App.getResources().getColor(R.color.color_primary))
                addShowLessText("Less")
                addShowMoreText("Read More")
            }
            flBack.setOnClickListener {
                onBackPressed()
            }
            btnBookNow.setOnClickListener {
                startActivity(Intent(this@HotelDetailActivity, LoginActivity::class.java))
            }
        }
    }

    override fun setUpObserver() {

    }
}