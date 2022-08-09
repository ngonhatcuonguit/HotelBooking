package com.cuongngo.hotel_booking.base.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.ui.MainActivity

inline fun <reified T : FragmentActivity> T?.pushFragment(
    fragment: Fragment,
    containerId: Int = R.id.container,
) {
    this?.supportFragmentManager
        ?.beginTransaction()
        ?.setCustomAnimations(
            R.anim.sliding_in_left,
            R.anim.sliding_out_right,
            R.anim.slide_in_left_custom,
            R.anim.slide_out_right_custom
        )
        ?.addToBackStack(T::class.java.simpleName)
        ?.replace(containerId, fragment)
        ?.commit()
}

fun AppCompatActivity.onFragmentBackPressed() {
    if (supportFragmentManager.backStackEntryCount <= 1)
        this.finish()
    else
        supportFragmentManager.popBackStack()
}

fun Activity.backToHome() {
    this.startActivity(Intent(this, MainActivity::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    })
}