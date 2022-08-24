package com.cuongngo.hotel_booking.utils

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.getDateString(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val date = this.time
    return dateFormat.format(date)
}

fun convertDateTimeForParamApi(dateString:String):String{
    val OLD_FORMAT = "dd/MM/yyyy"
    val NEW_FORMAT = "dd-MM-yyyy"
    try {
        val sdf = SimpleDateFormat(OLD_FORMAT, Locale.ROOT)
        val d = sdf.parse(dateString)
        sdf.applyPattern(NEW_FORMAT)
        return sdf.format(d)
    }catch (e:Throwable){
        e.printStackTrace()
        return dateString
    }
}

fun convertDateTimeForParamApi2(dateString: String): String {
    val OLD_FORMAT = "yyyy-MM-dd"
    val NEW_FORMAT = "dd-MM-yyyy"
    try {
        val sdf = SimpleDateFormat(OLD_FORMAT, Locale.ROOT)
        val d = sdf.parse(dateString)
        sdf.applyPattern(NEW_FORMAT)
        return sdf.format(d)
    } catch (e: Throwable) {
        e.printStackTrace()
        return dateString
    }
}
fun convertDateTimeForParamApi3(dateString: String): String {
    val OLD_FORMAT = "dd/MM/yyyy"
    val NEW_FORMAT = "yyyy-MM-dd"
    try {
        val sdf = SimpleDateFormat(OLD_FORMAT, Locale.ROOT)
        val d = sdf.parse(dateString)
        sdf.applyPattern(NEW_FORMAT)
        return sdf.format(d)
    } catch (e: Throwable) {
        e.printStackTrace()
        return dateString
    }
}