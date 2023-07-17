package io.software.databasemanager.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_rated_table")
data class MovieTopRated(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "dateRelease") val dateRelease: String,
    @ColumnInfo(name = "posterPath") val posterPath: String
)