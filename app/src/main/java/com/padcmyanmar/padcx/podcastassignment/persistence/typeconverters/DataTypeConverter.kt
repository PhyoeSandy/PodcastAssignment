package com.padcmyanmar.padcx.podcastassignment.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/2/2020.
 */
class DataTypeConverter {

    @TypeConverter
    fun toString(data: DataVO) : String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun todata(dataJsonString: String) : DataVO {
        val dataType = object : TypeToken<DataVO>() {}.type
        return Gson().fromJson(dataJsonString,dataType)
    }

}