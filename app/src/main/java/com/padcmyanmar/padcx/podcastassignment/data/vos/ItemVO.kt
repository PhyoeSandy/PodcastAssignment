package com.padcmyanmar.padcx.podcastassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.DataTypeConverter

@Entity(tableName = "playlist")
data class ItemVO(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("data") val data: DataVO,

    var status: Int = 0 // 1 for download
)