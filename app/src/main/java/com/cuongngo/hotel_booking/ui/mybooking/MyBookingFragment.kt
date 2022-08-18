package com.cuongngo.hotel_booking.ui.mybooking

import android.content.Intent
import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.view.DialogUtils
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.common.collection.EndlessRecyclerViewScrollListener
import com.cuongngo.hotel_booking.databinding.FragmentMyBookingBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.BookingModel
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.auth.LoginActivity
import com.cuongngo.hotel_booking.ui.bookingdetail.ViewTicketActivity
import com.cuongngo.hotel_booking.ui.profile.LogoutBottomSheetFragment

class MyBookingFragment : BaseFragmentMVVM<FragmentMyBookingBinding, MyBookingViewModel>(),
    CancelBookingBottomSheetFragment.CancelBooking, MyBookingAdapter.Listener {

    override val viewModel: MyBookingViewModel by kodeinViewModel()

    private lateinit var myBookingAdapter: MyBookingAdapter
    private var filter = 0
    private var itemId : Int? = null
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun inflateLayout() = R.layout.fragment_my_booking

    override fun setUp() {
        viewModel.getListMyBooking()

        with(binding){
            tvOngoing.setOnClickListener {
                handleChooseFilter(1)
            }
            tvComplete.setOnClickListener {
                handleChooseFilter(2)
            }
            tvCancel.setOnClickListener {
                handleChooseFilter(3)
            }
        }
        val layoutManager = LinearLayoutManager(requireContext())
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMoreListBooking()
            }
        }
    }

    override fun onCancelSelected() {
        viewModel.cancelBooking(itemId ?: return)
    }

    override fun onCancel(booking_id: Int) {
        itemId = booking_id
        CancelBookingBottomSheetFragment(this).show(childFragmentManager, CancelBookingBottomSheetFragment.TAG)
    }

    override fun onViewTicket(booking_id: Int) {
        startActivity(ViewTicketActivity.newIntent(requireContext(),booking_id ))
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.cancelBooking){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when (it.data?.result_code) {
                        1 -> {
                            viewModel.getListMyBooking()
                        }
                        300,301,302 -> {
                            showDialog(
                                title = "Chú ý",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Chú ý", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                }
            )
        }

        observeLiveDataChanged(viewModel.listMyBooking) {
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when (it.data?.result_code) {
                        1 -> {
                            setupRcvHotel(it.data.data?.bookings.orEmpty())
                        }
                        300,301,302 -> {
                            showDialog(
                                title = "Chú ý",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Chú ý", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                }
            )
        }
    }

    private fun setupRcvHotel(listMyBooking: List<BookingModel>){
        myBookingAdapter = MyBookingAdapter(
            arrayListOf(),
            this
        )
        myBookingAdapter.submitListBooking(listMyBooking)
        binding.rcvListMyBooking.adapter = myBookingAdapter
    }

    private fun handleChooseFilter(value: Int){
        if (value != filter){
            filter = value
            viewModel.filter = value
            when (filter){
                1 -> {
                    binding.tvOngoing.setBackgroundColor(Color.parseColor("#1AB65C"))
                    binding.tvOngoing.setTextColor(Color.parseColor("#FFFFFF"))

                    binding.tvComplete.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvComplete.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvCancel.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvCancel.setTextColor(Color.parseColor("#1AB65C"))

                    viewModel.getListMyBooking()
                }
                2 -> {
                    binding.tvOngoing.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvOngoing.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvComplete.setBackgroundColor(Color.parseColor("#1AB65C"))
                    binding.tvComplete.setTextColor(Color.parseColor("#FFFFFF"))

                    binding.tvCancel.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvCancel.setTextColor(Color.parseColor("#1AB65C"))
                    viewModel.getListMyBooking()
                }

                3 -> {
                    binding.tvOngoing.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvOngoing.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvComplete.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvComplete.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvCancel.setBackgroundColor(Color.parseColor("#1AB65C"))
                    binding.tvCancel.setTextColor(Color.parseColor("#FFFFFF"))

                    viewModel.getListMyBooking()
                }
            }
        }

    }

    companion object {
        val TAG = MyBookingFragment::class.java.simpleName
    }
}