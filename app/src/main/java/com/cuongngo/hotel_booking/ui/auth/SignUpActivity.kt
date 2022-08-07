package com.cuongngo.hotel_booking.ui.auth

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    override fun inflateLayout() = R.layout.activity_sign_up

    override fun setUp() {
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
        }

    }

    override fun setUpObserver() {

    }

    companion object {
        val TAG = SignUpActivity::class.java.simpleName
    }

}