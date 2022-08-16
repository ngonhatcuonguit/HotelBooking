package com.cuongngo.hotel_booking.ui.home

import android.content.Intent
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.common.collection.EndlessRecyclerViewScrollListener
import com.cuongngo.hotel_booking.databinding.ActivityHomeIndexBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailActivity

class HomeIndexActivity : AppBaseActivityMVVM<ActivityHomeIndexBinding, HomeViewModel>(), HotelAdapter.SelectedListener {

    override val viewModel: HomeViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_home_index

    private lateinit var hotelAdapter: HotelAdapter

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun setUp() {
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
        viewModel.getListHotel()

        val layoutManager = LinearLayoutManager(this)
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMoreListHotel()
            }
        }
    }

    private fun setupRcvHotel(listHotel: List<HotelModel>){
        hotelAdapter = HotelAdapter(
            arrayListOf(),
            this
        )
        hotelAdapter.submitListHotel(listHotel.toList())
        binding.rcvListHotel.adapter = hotelAdapter

    }

    override fun onHotelSelectedListener(hotelModel: HotelModel) {
        startActivity(HotelDetailActivity.newIntent(this,hotelModel.id))
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.listHotel){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    if (it.data?.result_code == 1){
                        if (!it.data?.cursors?.after.isNullOrEmpty()){
                            viewModel.after = it.data?.cursors?.after.orEmpty()
                        }
                        setupRcvHotel(it.data?.data?.hotels.orEmpty())
                    }else {
                        showDialog("Warning", it.data?.result)
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog("Warning", it.data?.result)
                }
            )
        }
    }

    companion object {
        val TAG = HomeIndexActivity::class.java.simpleName
    }
}