package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.padcx.podcastassignment.network.responses.DetailsResponse

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/5/2020.
 */
@Dao
interface DetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(detail: DetailsResponse)

    /*@Query("SELECT * FROM details WHERE id=:id")
    fun getDetailsById(id: String) : LiveData<DetailsVO>*/

    @Query("SELECT * FROM details")
    fun getDetailsById() : LiveData<DetailsResponse>
}