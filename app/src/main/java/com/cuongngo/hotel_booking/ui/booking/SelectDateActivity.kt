package com.cuongngo.hotel_booking.ui.booking

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivitySelectDateBinding

class SelectDateActivity : BaseActivity<ActivitySelectDateBinding>() {

    override fun inflateLayout() = R.layout.activity_select_date

    override fun setUp() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun setUpObserver() {

    }
}