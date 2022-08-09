package com.cuongngo.hotel_booking.ui.booking

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentPaymentBinding

class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {

    override fun inflateLayout() = R.layout.fragment_payment

    override fun setUp() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.btnContinue.setOnClickListener{
            SuccessDialogFragment().show(childFragmentManager, SuccessDialogFragment.TAG)
        }
    }

    override fun setUpObserver() {

    }
}