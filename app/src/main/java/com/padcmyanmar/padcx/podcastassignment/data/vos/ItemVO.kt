package com.padcmyanmar.padcx.podcastassignment.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters.DataTypeConverter

@IgnoreExtraProperties
@Entity(tableName = "playlist")
data class ItemVO(
    @PrimaryKey
    @SerializedName("id") var id: Int = 0,
    @SerializedName("data") val data: DataVO? = null,

    var status: Int = 0 // 1 for download
)