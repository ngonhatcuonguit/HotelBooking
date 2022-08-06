package com.cuongngo.hotel_booking.ui.hoteldetail

import android.content.Intent
import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivityHotelDetailBinding
import com.cuongngo.hotel_booking.response.GalleryModel
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.ui.auth.LoginActivity
import com.cuongngo.hotel_booking.ui.home.HotelAdapter

class HotelDetailActivity : BaseActivity<ActivityHotelDetailBinding>() {

    private lateinit var galleryAdapter: GalleryAdapter
    var listGallery = arrayOf(
        GalleryModel(
            1,
            ""
        ),
        GalleryModel(
            2,
            ""
        ),
        GalleryModel(
            3,
            ""
        ),
        GalleryModel(
            4,
            ""
        ),
        GalleryModel(
            5,
            ""
        ),
        GalleryModel(
            6,
            ""
        ),
        GalleryModel(
            7,
            ""
        ),
        GalleryModel(
            8,
            ""
        )
    )

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
        setupRcvgallery()
    }

    private fun setupRcvgallery(){
        galleryAdapter = GalleryAdapter(
            arrayListOf()
        )

        galleryAdapter.submitListGallery(listGallery.toList())
        binding.rcvGallery.adapter = galleryAdapter
    }

    override fun setUpObserver() {

    }
}