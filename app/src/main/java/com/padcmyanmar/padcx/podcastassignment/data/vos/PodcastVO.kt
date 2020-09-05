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


    /* val listennotes_url: String,
     val publisher: String,
     val thumbnail: String,
     val country: String,
      val earliest_pub_date_ms: Long,
    val email: String,
    val explicit_content: Boolean,
    val genre_ids: List<Int>,
    val is_claimed: Boolean,
    val itunes_id: Int,
    val language: String,
    val latest_pub_date_ms: Long,
    val rss: String,
    val total_episodes: Int,
    val type: String,
    val website: String,*/
)