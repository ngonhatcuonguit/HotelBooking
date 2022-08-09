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
    var listHotel = arrayOf(
        HotelModel(
            1,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
        HotelModel(
            2,
            4.9F,
            "Nevada Hotel",
            "HCM, Viet Nam",
            150,
            "https://www.traveloka.com/vi-vn/hotel/vietnam/z-hotel-sai-gon-9000000209252",
            8000
        ),
        HotelModel(
            3,
            4.0F,
            "Oriental Hotel",
            "Lagos, Nigeria",
            205,
            "",
            4000
        ),
        HotelModel(
            4,
            3.8F,
            "Federal Palace Hotel",
            "Ha Noi, Viet Nam",
            300,
            "https://www.booking.com/hotel/vn/yes-da-nang.vi.html",
            9000
        ),
        HotelModel(
            5,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
        HotelModel(
            6,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
        HotelModel(
            7,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
        HotelModel(
            8,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
        HotelModel(
            9,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
        HotelModel(
            10,
            5.0F,
            "Intercontinental Hotel",
            "Lagos, Nigeria",
            200,
            "https://www.agoda.com/vi-vn/l-hotel/hotel/khon-kaen-th.html",
            4000
        ),
    )

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