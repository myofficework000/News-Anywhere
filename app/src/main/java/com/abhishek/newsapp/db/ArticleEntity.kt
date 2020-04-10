package com.abhishek.newsapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.abhishek.newsapp.NewsConstants

/**
 * Created by abhishek.pathak on 08/04/2020.
 */

@Entity(tableName = NewsConstants.T_ARTICLE)
data class ArticleEntity(
    @PrimaryKey
    var title: String?,
    var source: String?,
    var author: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?
)