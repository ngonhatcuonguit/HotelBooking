package com.cuongngo.hotel_booking.ui.auth

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun inflateLayout() = R.layout.activity_login

    override fun setUp() {
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
            btnSignIn.setOnClickListener {
                finish()
            }
        }
    }

    override fun setUpObserver() {

    }
}