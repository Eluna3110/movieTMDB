package io.software.tmdbmovie.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.software.databasemanager.db.MovieDB
import io.software.databasemanager.entities.MoviePopular
import io.software.databasemanager.entities.MovieTopRated
import io.software.servicecoordinator.domain.response.Movie
import io.software.servicecoordinator.service.MovieAPI
import io.software.tmdbmovie.movie.MovieError
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository @Inject constructor(private val movieAPI: MovieAPI, private val movieDB : MovieDB)
{
    private val _error = MutableLiveData<MovieError>()
    val error: LiveData<MovieError>
        get() = _error

    private val _listMoviePopular = MutableLiveData<MutableList<Movie.Results>>()
    val listMoviePopular: LiveData<MutableList<Movie.Results>>
        get() = _listMoviePopular

    private var listEntityMoviePopular = listOf<MoviePopular>()

    fun getMoviesPopular(page: Int, apiKey: String, language: String) {
        val responseCall : Call<Movie?>? =
            movieAPI.getListMoviePopular(api_key = apiKey, language = language, page = page)
        responseCall?.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                _listMoviePopular.postValue(response.body()!!.results!!)
                val listEntity = arrayListOf<MoviePopular>()
                response.body()!!.results!!.forEach { movie ->
                    val entity = MoviePopular(
                        movie.id.toString(),
                        movie.title!!,
                        movie.release_date!!,
                        movie.poster_path!!
                    )
                    listEntity.add(entity)
                }
                listEntityMoviePopular = listEntity
            }
            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                _error.postValue(MovieError.ERROR_NETWORK)
            }
        })
    }

    private val _listMovieRated = MutableLiveData<MutableList<Movie.Results>>()
    val listMovieRated: LiveData<MutableList<Movie.Results>>
        get() = _listMovieRated

    private var listEntityMovieRated = listOf<MovieTopRated>()

    fun getMoviesRated(page: Int, apiKey: String, language: String) {
        val responseCall : Call<Movie?>? =
            movieAPI.getListMovieTopRater(api_key = apiKey, language = language, page = page)
        responseCall?.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                _listMovieRated.postValue(response.body()!!.results!!)
                val listEntity = arrayListOf<MovieTopRated>()
                response.body()!!.results!!.forEach { movie ->
                    val entity = MovieTopRated(
                        movie.id.toString(),
                        movie.title!!,
                        movie.release_date!!,
                        movie.poster_path!!
                    )
                    listEntity.add(entity)
                }
                listEntityMovieRated = listEntity
            }
            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                _error.postValue(MovieError.ERROR_NETWORK)
            }
        })
    }

    suspend fun addMoviePopularDB(){
        movieDB.getMovieDao().addMoviePopular(listEntityMoviePopular)
    }

    suspend fun addMovieRatedDB(){
        movieDB.getMovieDao().addMovieRated(listEntityMovieRated)
    }

    suspend fun getAllMoviePopularDB(){
        val movies = movieDB.getMovieDao().getMoviesPopular()
        movies.forEach {m ->
            val movieNew = Movie.Results()
            movieNew.id = m.id.toInt()
            movieNew.title = m.title
            movieNew.poster_path = m.posterPath

            _listMoviePopular.value?.add(movieNew)
        }

        getAllMovieRatedDB()
    }

    private suspend fun getAllMovieRatedDB(){
        val movies = movieDB.getMovieDao().getMoviesRated()
        movies.forEach {m ->
            val movieNew = Movie.Results()
            movieNew.id = m.id.toInt()
            movieNew.title = m.title
            movieNew.poster_path = m.posterPath

            _listMovieRated.value?.add(movieNew)
        }
    }
}