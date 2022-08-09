package com.cuongngo.hotel_booking.ui.auth

import com.cuongngo.cinemax.utils.Constants.Key.Companion.ACTION
import com.cuongngo.cinemax.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private val action by lazy { intent.getStringExtra(ACTION) ?:"" }

    override fun inflateLayout() = R.layout.activity_sign_up

    override fun setUp() {
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
        }

        if (action == EDIT_PROFILE){
            binding.tvTitle.text = "Edit Profile"
            binding.btnSignUp.text ="Update"
        }

    }

    override fun setUpObserver() {

    }

    companion object {
        val TAG = SignUpActivity::class.java.simpleName
    }

}