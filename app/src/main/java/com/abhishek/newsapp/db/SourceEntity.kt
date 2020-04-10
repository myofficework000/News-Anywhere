package com.abhishek.newsapp.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.abhishek.newsapp.NewsConstants

/**
 * Created by abhishek.pathak on 08/04/2020.
 */

@Entity(tableName = NewsConstants.T_SOURCE)
data class SourceEntity(
    @PrimaryKey()
    var id: String?,
    var name: String,
    var description: String,
    var url: String?,
    var category: String,
    var language: String,
    var country: String?
)