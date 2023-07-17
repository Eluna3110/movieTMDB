package io.software.tmdbmovie.maps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.software.tmdbmovie.maps.model.LocationInfo
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor() : ViewModel()
{
    private val _locationInfo = MutableLiveData<LocationInfo>()
    val locationInfo : LiveData<LocationInfo>
        get() = _locationInfo

    fun setLocationInfo(locationInfo: LocationInfo){
        _locationInfo.value = locationInfo
    }
}