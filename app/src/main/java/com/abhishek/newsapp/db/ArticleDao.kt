package com.abhishek.newsapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
@Dao
interface ArticleDao {

//    Using arg0 is way around, some issue with kotlin gradle plugin
//    expecting fix in further releases
    @Query("SELECT * FROM t_article WHERE source = :source1")
    fun getArticlesBySource(source1: String): LiveData<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle(articleList: List<ArticleEntity>)

    @Delete
    fun deleteArticle(articleList: List<ArticleEntity>)

}