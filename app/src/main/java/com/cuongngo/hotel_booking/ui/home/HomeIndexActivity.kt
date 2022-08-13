package com.cuongngo.hotel_booking.ui.home

import android.content.Intent
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivityHomeIndexBinding
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailActivity

class HomeIndexActivity : BaseActivity<ActivityHomeIndexBinding>(), HotelAdapter.SelectedListener {
    override fun inflateLayout() = R.layout.activity_home_index

    private lateinit var hotelAdapter: HotelAdapter
    var listHotel = arrayListOf<HotelModel>()

    override fun setUp() {
        setupRcvHotel()
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupRcvHotel(){
        hotelAdapter = HotelAdapter(
            arrayListOf(),
            this
        )

        hotelAdapter.submitListHotel(listHotel.toList())
        binding.rcvListHotel.adapter = hotelAdapter

    }

    override fun onHotelSelectedListener(hotelModel: HotelModel) {
        startActivity(Intent(this, HotelDetailActivity::class.java))
    }

    override fun setUpObserver() {

    }

    companion object {
        val TAG = HomeIndexActivity::class.java.simpleName
    }
}