package com.cuongngo.hotel_booking.di

import androidx.lifecycle.ViewModelProvider
import com.cuongngo.hotel_booking.base.viewmodel.bindViewModel
import com.cuongngo.hotel_booking.services.HotelApi
import com.cuongngo.hotel_booking.services.network.invoker.NetworkConnectionInterceptor
import com.cuongngo.hotel_booking.services.remote.HotelRemoteDataSource
import com.cuongngo.hotel_booking.services.remote.UserRemoteDataSource
import com.cuongngo.hotel_booking.services.repository.HotelRepository
import com.cuongngo.hotel_booking.services.repository.UserRepository
import com.cuongngo.hotel_booking.ui.auth.UserViewModel
import com.cuongngo.hotel_booking.ui.home.HomeViewModel
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

const val APP_MODULE = "app_module"

val appModule = Kodein.Module(APP_MODULE, false) {
    bind() from singleton { NetworkConnectionInterceptor(instance()) }
    bind() from singleton { HotelApi() }

    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
    bind() from singleton { UserRemoteDataSource(instance()) }
    bind() from singleton { HotelRemoteDataSource(instance())}
    bind() from singleton { UserRepository(instance()) }
    bind() from singleton { HotelRepository(instance()) }

    bindViewModel<UserViewModel>() with provider {
        UserViewModel(instance())
    }

    bindViewModel<HomeViewModel>() with provider {
        HomeViewModel(instance())
    }

    bindViewModel<HotelDetailViewModel>() with provider {
        HotelDetailViewModel(instance())
    }

}