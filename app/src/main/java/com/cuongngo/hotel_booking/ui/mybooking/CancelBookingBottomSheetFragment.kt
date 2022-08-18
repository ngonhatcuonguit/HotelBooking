package com.cuongngo.hotel_booking.ui.mybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.FragmentBottomSheetCancelBookingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CancelBookingBottomSheetFragment(private var cancelBooking: CancelBooking) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetCancelBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bottom_sheet_cancel_booking,
            container,
            false
        )
        return binding.root
    }

    private fun setUp() {
        binding.btnYes.setOnClickListener {
            cancelBooking.onCancelSelected()
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    interface CancelBooking {
        fun onCancelSelected()
    }

    companion object {
        val TAG = CancelBookingBottomSheetFragment::class.java.simpleName

    }

}