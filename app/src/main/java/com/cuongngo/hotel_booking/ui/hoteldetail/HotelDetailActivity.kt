package com.cuongngo.hotel_booking.ui.hoteldetail

import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivityHotelDetailBinding

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
        }
    }

    override fun setUpObserver() {

    }
}