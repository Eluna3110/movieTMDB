package io.software.tmdbmovie.movie.view

import io.software.servicecoordinator.domain.response.Movie

interface IMovieListener
{
    fun onMovieDetail(movie : Movie.Results)
}