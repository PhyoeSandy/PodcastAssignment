package com.padcmyanmar.padcx.podcastassignment.network.impl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.ItemVO
import com.padcmyanmar.padcx.podcastassignment.network.FirebaseApi
import com.padcmyanmar.padcx.podcastassignment.network.responses.RandomPodcastVO
import java.util.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
object RealtimeDatabaseFirebaseApiImpl : FirebaseApi {
    private val database = Firebase.database.reference

    override fun getRandomPodcast(
        onSuccess: (podcast: RandomPodcastVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("random_podcast").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(RandomPodcastVO::class.java)?.let{
                    onSuccess(it)
                }
            }
        })
    }

    override fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("category").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList: MutableList<CategoryVO> = arrayListOf()
                snapshot.children.forEach { dataSnapshot ->
                    dataSnapshot.getValue(CategoryVO::class.java)?.let {
                        categoryList.add(it)
                    }
                }
                onSuccess(categoryList)
            }
        })
    }

    override fun getPlayListPodcasts(
        onSuccess: (itemList: List<ItemVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("playlist").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList: MutableList<ItemVO> = arrayListOf()
                snapshot.children.forEach { dataSnapshot ->
                    dataSnapshot.getValue(ItemVO::class.java)?.let {
                        categoryList.add(it)
                    }
                }
                onSuccess(categoryList)
            }
        })
    }
}