package com.cuongngo.hotel_booking.ui.booking

import android.os.Bundle
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.dialog.BaseDialog
import com.cuongngo.hotel_booking.base.view.ProgressDialog
import com.cuongngo.hotel_booking.databinding.DialogSuccessBinding

class SuccessDialogFragment : BaseDialog<DialogSuccessBinding>() {

//    private var success: (() -> Unit)? = null

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
            activity?.finish()
        }
        binding.btnViewTicket.setOnClickListener {
            dismiss()
            activity?.finish()
        }
    }

    override fun setUpObserver() {

    }

//    fun onSuccess(success: (() -> Unit)?): SuccessDialogFragment {
//        this.success = success
//        return this
//    }

    companion object {
        val TAG = SuccessDialogFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(): SuccessDialogFragment {
            return SuccessDialogFragment()
        }
    }

}