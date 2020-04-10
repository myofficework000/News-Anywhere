package com.abhishek.newsapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.abhishek.newsapp.NewsConstants

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
@Dao
interface SourceDao {

    @Query("SELECT * FROM " + NewsConstants.T_SOURCE)
    fun getAllNewsSource(): LiveData<List<SourceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(source: List<SourceEntity>)

    @Delete
    fun deleteSource(source: List<SourceEntity>)

//    fun insertSources(source: List<Source>) {
//
//        insertSources(*sourceEntityArray.toTypedArray())
//    }
}