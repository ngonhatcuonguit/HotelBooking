package com.cuongngo.hotel_booking.services.network.invoker

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)