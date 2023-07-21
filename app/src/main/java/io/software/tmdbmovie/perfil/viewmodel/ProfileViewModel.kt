package io.software.tmdbmovie.perfil.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.software.tmdbmovie.R
import io.software.tmdbmovie.datasource.Repository
import io.software.tmdbmovie.perfil.model.Profile
import io.software.tmdbmovie.perfil.view.ListItemAdapter
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel()
{
    private val _listProfile = MutableLiveData<MutableList<Profile>>()
    val listProfile : LiveData<MutableList<Profile>>
        get() = _listProfile

    private val _adapterList = MutableLiveData<ListItemAdapter>()
    val adapterList : LiveData<ListItemAdapter>
        get() = _adapterList

    private val _apiKey = MutableLiveData<String>()
    val apiKey : LiveData<String>
        get() = _apiKey

    private val _language = MutableLiveData<String>()
    val language : LiveData<String>
        get() = _language

    private val _uri = MutableLiveData<Uri>()
    val uri : LiveData<Uri>
        get() = _uri

    init {
        _apiKey.value = "f1cca3de1acdb7a36b961de832f21eb3"
        _language.value = "en-US"
        _listProfile.value = mutableListOf()
    }

    fun setUri(uri : Uri){
        _uri.value = uri
    }

    fun initAdapter(context: Context){
        _listProfile.value = mutableListOf()

        _listProfile.value?.add(Profile(R.drawable.notifications,context.getString(R.string.profile_notificaciones)))
        _listProfile.value?.add(Profile(R.drawable.settings,context.getString(R.string.profile_configuration)))
        _listProfile.value?.add(Profile(R.drawable.person,context.getString(R.string.profile_account)))
        _listProfile.value?.add(Profile(R.drawable.help,context.getString(R.string.profile_ayuda)))
        _adapterList.value = ListItemAdapter()

    }
}