package com.padcmyanmar.padcx.podcastassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/23/2020.
 */
@IgnoreExtraProperties
@Entity(tableName = "podcast")
data class PodcastVO(
    @PrimaryKey
    @SerializedName("id") var id: String = "",
    @SerializedName("image") var image: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("description") var description: String = ""
)