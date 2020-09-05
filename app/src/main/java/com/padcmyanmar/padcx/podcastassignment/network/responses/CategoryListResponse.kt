package com.padcmyanmar.padcx.podcastassignment.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
data class CategoryListResponse(
    @SerializedName("genres") val genres: List<CategoryVO> = arrayListOf()
)