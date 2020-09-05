package com.padcmyanmar.padcx.podcastassignment.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "playlist")
data class PlaylistsVO(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String

 /*   val last_timestamp_ms: Long,
    val listennotes_url: String,
    val name: String,
    val podcast_count: Int,
    val thumbnail: String,
    val visibility: String*/
)