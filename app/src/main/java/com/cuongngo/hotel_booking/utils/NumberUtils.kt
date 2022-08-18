package com.cuongngo.hotel_booking.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

const val ZERO = 0

fun doubleToStringNoDecimal(d: Double): String? {
    val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
    formatter.applyPattern("#,###")
    return formatter.format(d)
}

fun Int.toPrice() = String.format("%,d", this)

fun Long.formatToPrice(): String = NumberFormat.getInstance(Locale("vi","VN")).format(this)

fun Int?.formatToPrice() = NumberFormat.getInstance(Locale("vi","VN")).format(this)

/**
 * Round to certain number of decimals
 *
 * @param d
 * @param decimalPlace
 * @return
 */
fun round(d: Float, decimalPlace: Int): Float {
    var bd = BigDecimal(d.toString())
    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP)
    return bd.toFloat()
}
