package com.padcmyanmar.padcx.podcastassignment.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.padcx.podcastassignment.data.vos.CategoryVO

/**
 * Created by Phyoe Sandy Soe Tun
 * on 9/4/2020.
 */
@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categories: List<CategoryVO>)

    @Query("SELECT * FROM category")
    fun getAllCategories() : LiveData<List<CategoryVO>>
}