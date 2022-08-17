package com.cuongngo.hotel_booking.ui.booking

import androidx.core.os.bundleOf
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModelFromActivity
import com.cuongngo.hotel_booking.databinding.FragmentPaymentBinding
import com.cuongngo.hotel_booking.ext.WTF
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.response.SetUpBeforeBookingModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.utils.convertDateTimeForParamApi

class PaymentFragment : BaseFragmentMVVM<FragmentPaymentBinding, BookingViewModel>() {

    override val viewModel: BookingViewModel by kodeinViewModelFromActivity()

    override fun inflateLayout() = R.layout.fragment_payment

    private val beforeBookingModel by lazy {
        arguments?.getSerializable(BookingActivity.BEFORE_BOOKING_KEY) as? SetUpBeforeBookingModel
    }

    override fun setUp() {
        WTF("setup ${viewModel.setUpBeforeBookingModel}")

        viewModel.setUpBeforeBookingModel = beforeBookingModel!!

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.layoutHotelItem.hotel = viewModel.setUpBeforeBookingModel.hotelModel
        binding.btnContinue.setOnClickListener{
            viewModel.booking(
                viewModel.setUpBeforeBookingModel.guest ?: return@setOnClickListener,
                convertDateTimeForParamApi(viewModel.setUpBeforeBookingModel.check_in.orEmpty()),
                convertDateTimeForParamApi(viewModel.setUpBeforeBookingModel.check_out.orEmpty()),
                viewModel.setUpBeforeBookingModel.hotel_id ?:return@setOnClickListener,
            )
        }
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.booking){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    if (it.data?.result_code == 1){
                        SuccessDialogFragment().show(childFragmentManager, SuccessDialogFragment.TAG)
                    }else {
                        showDialog("Chú ý", message = it.data?.result)
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