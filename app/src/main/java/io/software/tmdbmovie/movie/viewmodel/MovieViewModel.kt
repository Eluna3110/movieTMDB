package io.software.tmdbmovie.movie.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.software.servicecoordinator.domain.response.Movie
import io.software.tmdbmovie.datasource.Repository
import io.software.tmdbmovie.movie.MovieError
import io.software.tmdbmovie.movie.view.MovieAdapter
import io.software.tmdbmovie.movie.view.MovieFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    private val _movieResult = MutableLiveData<Movie.Results>()
    val movieResult : LiveData<Movie.Results>
        get() = _movieResult

    private val _listMoviePopular = MutableLiveData<MutableList<Movie.Results>>()
    val listMoviePopular : LiveData<MutableList<Movie.Results>>
        get() = repository.listMoviePopular

    private val _movieAdapterPopular = MutableLiveData<MovieAdapter>()
    val movieAdapterPopular : LiveData<MovieAdapter>
        get() = _movieAdapterPopular

    private val _listMovieRated = MutableLiveData<MutableList<Movie.Results>>()
    val listMovieRated : LiveData<MutableList<Movie.Results>>
        get() = repository.listMovieRated

    private val _movieAdapterRated = MutableLiveData<MovieAdapter>()
    val movieAdapterRated : LiveData<MovieAdapter>
        get() = _movieAdapterRated

    private val _apiKey = MutableLiveData<String>()
    val apiKey : LiveData<String>
        get() = _apiKey

    private val _language = MutableLiveData<String>()
    val language : LiveData<String>
        get() = _language

    private val _error = MutableLiveData<MovieError>()
    val error: LiveData<MovieError>
        get() = repository.error

    init {
        _apiKey.value = "f1cca3de1acdb7a36b961de832f21eb3"
        _language.value = "en-US"
        _movieResult.value = null
        _listMoviePopular.value = mutableListOf()
        _listMovieRated.value = mutableListOf()
    }

    fun init(listener: MovieFragment) {
        _movieAdapterPopular.value = MovieAdapter(mutableListOf(), listener, listener)
        _movieAdapterRated.value = MovieAdapter(mutableListOf(), listener, listener)
    }

    fun getMovieListPopular(){
        repository.getMoviesPopular(1, _apiKey.value!!, _language.value!!)
    }

    fun saveMoviePopularDB() {
        viewModelScope.launch {
            try {
                repository.addMoviePopularDB()
            } catch (e: Exception) {
                Log.e("DB ERROR", e.message.toString())
            }
        }
    }

    fun updateDataPopular(data: MutableList<Movie.Results>){
        _movieAdapterPopular.value!!.updateDataSet(data)
    }

    fun getMovieListRated(){
        repository.getMoviesRated(1, _apiKey.value!!, _language.value!!)
    }

    fun saveMovieRatedDB(){
        viewModelScope.launch {
            try {
                repository.addMovieRatedDB()
            } catch (e: Exception) {
                    Log.e("DB ERROR", e.message.toString())
            }
        }

    }

    fun updateDataRated(data: MutableList<Movie.Results>){
        _movieAdapterRated.value!!.updateDataSet(data)
    }

    fun getInfoDB(){
        viewModelScope.launch {
            repository.getAllMoviePopularDB()
        }
    }
}