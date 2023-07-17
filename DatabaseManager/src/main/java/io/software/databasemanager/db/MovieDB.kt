package io.software.databasemanager.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.software.databasemanager.entities.MoviePopular
import io.software.databasemanager.entities.MovieTopRated

@Database(entities = [MoviePopular::class, MovieTopRated::class], version = 1)
abstract class MovieDB : RoomDatabase()
{
    abstract fun getMovieDao() : MovieDAO
}