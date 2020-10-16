package com.padcmyanmar.padcx.podcastassignment.network.impl

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO
import com.padcmyanmar.padcx.podcastassignment.data.vos.DataVO
import com.padcmyanmar.padcx.podcastassignment.network.FirebaseApi

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/27/2020.
 */
object RealtimeDatabaseFirebaseApiImpl : FirebaseApi {
    private val database = Firebase.database.reference

    override fun getCategoryList(
        onSuccess: (categoryList: List<CategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("genres").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = arrayListOf<CategoryVO>()
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
        onSuccess: (itemList: List<DataVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("latest_episodes").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val playlist = arrayListOf<DataVO>()
                snapshot.children.forEach { dataSnapshot ->
                    dataSnapshot.getValue(DataVO::class.java)?.let {
                        playlist.add(it)
                    }
                }
                onSuccess(playlist)
            }
        })
    }
}