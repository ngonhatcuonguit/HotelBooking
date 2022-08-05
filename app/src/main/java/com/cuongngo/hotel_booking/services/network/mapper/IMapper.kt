package com.cuongngo.hotel_booking.services.network.mapper

interface IMapper<in I,out O>{
    fun map(input:I):O
}