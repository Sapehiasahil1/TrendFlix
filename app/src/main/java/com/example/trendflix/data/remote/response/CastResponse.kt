package com.example.trendflix.data.remote.response

import com.example.trendflix.model.Cast
import com.google.gson.annotations.SerializedName

data class CastResponse(

    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val castResult: List<Cast>
)
