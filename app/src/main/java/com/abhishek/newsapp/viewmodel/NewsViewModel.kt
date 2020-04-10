package com.abhishek.newsapp.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context
import com.abhishek.newsapp.api.Resource
import com.abhishek.newsapp.db.SourceEntity
import com.abhishek.newsapp.ui.api.APIInterface
import com.abhishek.newsapp.ui.model.ArticlesResponse
import com.abhishek.newsapp.repo.NewsRepository

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepo : NewsRepository = NewsRepository(APIInterface.getNewsAPIService())
    val context: Context = application.applicationContext

    fun getNewsSource(language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return newsRepo.fetchNewsSource(context,language, category, country)
    }

    fun getNewsArticles(source: String, sortBy: String?) : LiveData<ArticlesResponse> {
        return newsRepo.getNewsArticles(source, sortBy)
    }
}