package com.example.trendflix.data.remote.response

import com.example.trendflix.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)
