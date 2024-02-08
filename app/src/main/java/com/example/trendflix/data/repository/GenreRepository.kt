package com.example.trendflix.data.repository

import com.example.trendflix.data.remote.ApiService
import com.example.trendflix.data.remote.response.GenreResponse
import com.example.trendflix.util.FilmType
import com.example.trendflix.util.Resource
import javax.inject.Inject

class GenreRepository @Inject constructor(private val api: ApiService) {
    suspend fun getMoviesGenre(filmType: FilmType): Resource<GenreResponse> {
        val response = try {
            if (filmType == FilmType.MOVIE) api.getMovieGenres() else api.getTvShowGenres()
        } catch (e: Exception){
            return Resource.Error("Unknown error occurred!")
        }
        return Resource.Success(response)
    }
}