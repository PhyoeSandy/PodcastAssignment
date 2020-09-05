package com.padcmyanmar.padcx.podcastassignment.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.PodcastTypeConverter

@Entity(tableName = "random_podcast")
data class RandomPodcastVO(

    @PrimaryKey
    @SerializedName("id")  val id: String,
    @SerializedName("audio") val audio: String,
    @SerializedName("audio_length_sec")  val audio_length_sec: Int,
    @SerializedName("description")  val description: String,
    @SerializedName("explicit_content")  val explicit_content: Boolean,
    @SerializedName("image")  val image: String,

    @SerializedName("podcast") val podcast: PodcastVO

    /*
    val link: String,
    val listennotes_edit_url: String,
    val listennotes_url: String,
    val maybe_audio_invalid: Boolean,
    val pub_date_ms: Long,
    val thumbnail: String,
    val title: String*/
)