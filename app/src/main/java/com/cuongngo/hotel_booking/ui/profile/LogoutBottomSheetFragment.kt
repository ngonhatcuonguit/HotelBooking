package com.cuongngo.hotel_booking.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.FragmentBottomSheetLogoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LogoutBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bottom_sheet_logout,
            container,
            false
        )
        return binding.root
    }

    private fun setUp(){

    }

    companion object {
        val TAG = LogoutBottomSheetFragment::class.java.simpleName

    }

}