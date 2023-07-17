package io.software.tmdbmovie.maps.model

import com.google.errorprone.annotations.Keep

@Keep
data class LocationInfo(
    val location : List<Location> = listOf()
)

@Keep
data class Location(
    var label : String = "",
    var latitude : Double = 0.0,
    var length : Double = 0.0
)