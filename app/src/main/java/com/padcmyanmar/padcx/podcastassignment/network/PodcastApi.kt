package com.padcmyanmar.padcx.podcastassignment.network

import com.padcmyanmar.padcx.podcastassignment.network.responses.*
import com.padcmyanmar.padcx.podcastassignment.utils.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/2/2020.
 */
interface PodcastApi {
    @GET(RANDOM_PODCST)
    fun getRandomPodcast(@Header(PARAM_API_KEY) apiKey: String): Observable<RandomPodcastVO>

    @GET(CATEGORIES_LIST)
    fun getCategoryList(
        @Header(PARAM_API_KEY) apiKey: String,
        @Query(TOP_LEVEL) top: Int
    ): Observable<CategoryListResponse>

    @GET("$PLAYLIST_INFO/{id}")
    fun getPlayListPodcasts(
        @Header(PARAM_API_KEY) apiKey: String, @Path("id") id: String,
        @Query(TYPE) type: String, @Query(PAGINATION) pagination: Int,
        @Query(SORT) sort: String
        ): Observable<PlayListResponse>

    @GET("$DETAILS_EPISODE/{id}")
    fun getDetailsEpisode(
        @Header(PARAM_API_KEY) apiKey: String, @Path("id") id: String
    ): Observable<DetailsResponse>

}