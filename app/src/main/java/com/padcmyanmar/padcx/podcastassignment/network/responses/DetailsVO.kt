package com.padcmyanmar.padcx.podcastassignment.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.PodcastTypeConverter
import kotlin.math.tan

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
@Entity(tableName = "details")
data class DetailsVO(
    @SerializedName("audio")var audio: String,
    @SerializedName("audio_length_sec")var audio_length_sec: Int,
    @SerializedName("description")var description: String,
    @SerializedName("explicit_content")var explicit_content: Boolean,

    @PrimaryKey
    @SerializedName("id")var id: String,
    @SerializedName("image")var image: String,
    @SerializedName("link")var link: String,
    @SerializedName("podcast")var podcast: PodcastVO

    /*@SerializedName("listennotes_edit_url")var listennotes_edit_url: String,
    @SerializedName("listennotes_url")var listennotes_url: String,
    @SerializedName("maybe_audio_invarid")var maybe_audio_invarid: Boolean,
    @SerializedName("pub_date_ms")var pub_date_ms: Long,
    @SerializedName("thumbnail")var thumbnail: String,
    @SerializedName("title")var title: String
    */

)