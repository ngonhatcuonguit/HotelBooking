package com.cuongngo.hotel_booking.di

import androidx.lifecycle.ViewModelProvider
import com.cuongngo.hotel_booking.services.HotelApi
import com.cuongngo.hotel_booking.services.network.invoker.NetworkConnectionInterceptor
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

const val APP_MODULE = "app_module"

val appMovieModule = Kodein.Module(APP_MODULE, false) {
    bind() from singleton { NetworkConnectionInterceptor(instance()) }
    bind() from singleton { HotelApi() }

    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }

}