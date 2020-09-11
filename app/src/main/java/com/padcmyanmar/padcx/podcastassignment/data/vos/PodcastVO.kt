package com.padcmyanmar.padcx.podcastassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
@Entity(tableName = "podcast")
data class PodcastVO(
    @PrimaryKey
    @SerializedName("id") val id: String ="",
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)