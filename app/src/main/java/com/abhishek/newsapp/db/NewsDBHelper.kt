package com.abhishek.newsapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.abhishek.newsapp.NewsConstants

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
@Database(entities = arrayOf(SourceEntity::class, ArticleEntity::class), version = 1)
abstract class NewsDBHelper : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
    abstract fun getSourceDao(): SourceDao

    companion object {
        private var db: NewsDBHelper? = null

        fun getInstance(context: Context): NewsDBHelper {
            if (db == null) {
                db = Room.databaseBuilder(context, NewsDBHelper::class.java, NewsConstants.DB_NAME).build()
            }
            return db!!
        }
    }

}