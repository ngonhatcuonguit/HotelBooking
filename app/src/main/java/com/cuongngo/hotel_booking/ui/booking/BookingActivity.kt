package com.cuongngo.hotel_booking.ui.booking

import android.os.Bundle
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.base.view.onFragmentBackPressed
import com.cuongngo.hotel_booking.base.view.pushFragment
import com.cuongngo.hotel_booking.databinding.ActivityBookingBinding

class BookingActivity : BaseActivity<ActivityBookingBinding>() {

    override fun inflateLayout() = R.layout.activity_booking

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pushFragment(
            SelectDateFragment()
        )
        enableLightStatusBar()
    }

    override fun setUp() {

    }

    override fun onBackPressed() {
        if (isIgnoreBackPressed){
            //Do nothing, need customizing after
        }
        else {
            onFragmentBackPressed()
        }
    }

    override fun setUpObserver() {

    }
}