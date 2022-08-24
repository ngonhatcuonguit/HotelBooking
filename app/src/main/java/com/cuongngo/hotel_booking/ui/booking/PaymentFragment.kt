package com.cuongngo.hotel_booking.ui.booking

import android.content.Intent
import androidx.core.os.bundleOf
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.view.DialogUtils
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModelFromActivity
import com.cuongngo.hotel_booking.databinding.FragmentPaymentBinding
import com.cuongngo.hotel_booking.ext.WTF
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.SetUpBeforeBookingModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.auth.LoginActivity
import com.cuongngo.hotel_booking.utils.convertDateTimeForParamApi
import com.cuongngo.hotel_booking.utils.convertDateTimeForParamApi3
import com.cuongngo.hotel_booking.utils.formatToPrice

class PaymentFragment : BaseFragmentMVVM<FragmentPaymentBinding, BookingViewModel>() {

    override val viewModel: BookingViewModel by kodeinViewModelFromActivity()

    override fun inflateLayout() = R.layout.fragment_payment

    private val beforeBookingModel by lazy {
        arguments?.getSerializable(BookingActivity.BEFORE_BOOKING_KEY) as? SetUpBeforeBookingModel
    }

    override fun setUp() {
        WTF("setup ${viewModel.setUpBeforeBookingModel}")

        viewModel.setUpBeforeBookingModel = beforeBookingModel!!

        binding.bookingSetup = viewModel.setUpBeforeBookingModel

        val total = viewModel.setUpBeforeBookingModel.hotelModel?.price?.times(
            viewModel.setUpBeforeBookingModel.guest ?: 1
        )
        binding.totalPrice.text = total?.formatToPrice() + "đ"

        binding.tvTotal.text = total?.formatToPrice() + "đ"

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.layoutHotelItem.hotel = viewModel.setUpBeforeBookingModel.hotelModel
        binding.btnContinue.setOnClickListener {
            viewModel.booking(
                viewModel.setUpBeforeBookingModel.guest ?: return@setOnClickListener,
                convertDateTimeForParamApi3(viewModel.setUpBeforeBookingModel.check_in.orEmpty()),
                convertDateTimeForParamApi3(viewModel.setUpBeforeBookingModel.check_out.orEmpty()),
                viewModel.setUpBeforeBookingModel.hotel_id ?: return@setOnClickListener,
            )
        }
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.booking) {
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when (it.data?.result_code) {
                        1 -> {
                            SuccessDialogFragment(it.data.data?.id).show(
                                childFragmentManager,
                                SuccessDialogFragment.TAG
                            )
                        }
                        300, 301, 302 -> {

                            showDialog(
                                title = "Chú ý",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(
                                            Intent(
                                                requireContext(),
                                                LoginActivity::class.java
                                            )
                                        )
                                    }
                                })
                        }
                        else -> {
                            showDialog(title = "Chú ý", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog("Chú ý", message = it.data?.result)
                }
            )
        }
    }

    companion object {
        val TAG = PaymentFragment::class.java.simpleName

        fun instance(
            beforeBookingModel: SetUpBeforeBookingModel?
        ): PaymentFragment {
            return PaymentFragment().apply {
                arguments = bundleOf().apply {
                    putSerializable(BookingActivity.BEFORE_BOOKING_KEY, beforeBookingModel)
                }
            }
        }
    }


}