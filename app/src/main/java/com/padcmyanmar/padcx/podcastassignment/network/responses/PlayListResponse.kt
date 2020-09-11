package com.padcmyanmar.padcx.podcastassignment.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO

data class PlayListResponse(
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String,
    @SerializedName("items") val items: List<ItemVO> = listOf()
)