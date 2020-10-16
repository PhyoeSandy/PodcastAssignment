package com.padcmyanmar.padcx.podcastassignment.network.impl

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.PodcastVO
import com.padcmyanmar.padcx.podcastassignment.network.FirebaseApi

object CloudFirestoreApiImpl : FirebaseApi {
    val db = Firebase.firestore

    override fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("genres")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val categoryList: MutableList<CategoryVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        categoryList.add(convertCategoryVO(document))
                    }
                    onSuccess(categoryList)
                }
            }
    }

    override fun getPlayListPodcasts(
        onSuccess: (itemList: List<DataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("latest_episodes")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check connection")
                } ?: run {
                    val playList : MutableList<DataVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val hashmap = document.data
                        hashmap?.put("id", document.id)
                        val data = Gson().toJson(hashmap)
                        val playlist = Gson().fromJson<DataVO>(data, DataVO::class.java)
                        playList.add(playlist)
                        //playList.add(convertDataVO(document))
                    }

                    onSuccess(playList)
                }
            }
    }

    private fun convertCategoryVO(document: DocumentSnapshot) : CategoryVO {
         val data = document.data
         val category = CategoryVO()
         category.name = data?.get("name") as String
         category.id = (data["id"] as Long).toInt()
         category.image = data?.get("image") as String
         return category
    }

    /*private fun convertDataVO(document: DocumentSnapshot) : DataVO{
        val data = document.data
        val playlist = DataVO()
        playlist.id = data?.get("id") as String
        playlist.image = data?.get("image") as String
        playlist.audio = data?.get("audio") as String
        playlist.audio_length_sec = (data?.get("audio_length_sec") as Long).toInt()
        playlist.description = data?.get("description") as String
        playlist.title = data?.get("title") as String
        val podcastData  = data["podcast"] as Map<String,Any>
        val podcast = PodcastVO()
        podcast.id = podcastData?.get("id") as String
        podcast.image = podcastData?.get("image") as String
        podcast.title = podcastData?.get("title") as String
        playlist.podcast = podcast
        return playlist
    }*/
}