package com.cuongngo.hotel_booking.ui.booking

import android.os.Bundle
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.dialog.BaseDialog
import com.cuongngo.hotel_booking.base.view.ProgressDialog
import com.cuongngo.hotel_booking.databinding.DialogSuccessBinding

class SuccessDialogFragment : BaseDialog<DialogSuccessBinding>() {

    override fun inflateLayout() = R.layout.dialog_success

    override fun provideLoading(): ProgressDialog? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogSuccessThem)
    }

    override fun setUp() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun setUpObserver() {

    }

    companion object {
        val TAG = SuccessDialogFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): SuccessDialogFragment {
            return SuccessDialogFragment()
        }
    }

}