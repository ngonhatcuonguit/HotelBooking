package com.cuongngo.hotel_booking.ui.hoteldetail

import android.content.Context
import android.content.Intent
import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityHotelDetailBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.response.GalleryModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.auth.LoginActivity
import com.cuongngo.hotel_booking.ui.booking.BookingActivity
import com.cuongngo.hotel_booking.utils.loadImage

class HotelDetailActivity : AppBaseActivityMVVM<ActivityHotelDetailBinding, HotelDetailViewModel>() {

    override val viewModel: HotelDetailViewModel by kodeinViewModel()

    private lateinit var galleryAdapter: GalleryAdapter

    private val hotelID by lazy {
        intent.extras?.getInt(HOTEL_ID_KEY)
    }

    override fun inflateLayout() = R.layout.activity_hotel_detail

    override fun setUp() {
        viewModel.getDetailHotel(hotelID ?: return)
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
                if (AppPreferences.getUserAccessToken().isNullOrEmpty()){
                    startActivity(Intent(this@HotelDetailActivity, LoginActivity::class.java))
                }else startActivity(Intent(this@HotelDetailActivity, BookingActivity::class.java))
            }
        }
        setupRcvGallery()
    }

    private fun setupRcvGallery(){
        galleryAdapter = GalleryAdapter(
            arrayListOf()
        )
        binding.rcvGallery.adapter = galleryAdapter
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.hotelDetail){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    if (it.data?.result_code == 1){
                        binding.hotel = it.data.data
                        loadImage(binding.ivPoster, it.data.data?.images?.firstOrNull()?.medium.orEmpty())
                        galleryAdapter.submitListGallery(it.data.data?.images.orEmpty())
                    }else{
                        showDialog("Chú ý", message = it.data?.result)
                    }
                },
                onError = {
                    showDialog("Chú ý", message = it.data?.result)
                }
            )
        }
    }

    companion object{
        val TAG = HotelDetailActivity::class.java.simpleName

        const val HOTEL_ID_KEY = "HOTEL_ID_KEY"

        fun newIntent(context: Context, hotel_id: Int?) =
            Intent(context, HotelDetailActivity::class.java).apply {
                putExtra(
                    HOTEL_ID_KEY, hotel_id
                )
            }
    }

}