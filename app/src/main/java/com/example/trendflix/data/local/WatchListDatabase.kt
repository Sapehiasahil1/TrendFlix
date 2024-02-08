package com.example.trendflix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [MyListMovie::class],
    exportSchema = false
)
abstract class WatchListDatabase: RoomDatabase() {
    abstract val moviesDao: MoviesDao
}