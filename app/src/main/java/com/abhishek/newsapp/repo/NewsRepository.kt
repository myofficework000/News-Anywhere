package com.abhishek.newsapp.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.abhishek.newsapp.BuildConfig
import com.abhishek.newsapp.RateLimiter
import com.abhishek.newsapp.api.ApiResponse
import com.abhishek.newsapp.api.NetworkBoundResource
import com.abhishek.newsapp.api.Resource
import com.abhishek.newsapp.db.NewsDBHelper
import com.abhishek.newsapp.db.SourceEntity
import java.util.concurrent.TimeUnit
import com.abhishek.newsapp.ui.api.APIInterface
import com.abhishek.newsapp.ui.model.ArticlesResponse
import com.abhishek.newsapp.ui.model.SourceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class NewsRepository(private val apiInterface: APIInterface) {

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchNewsSource(context: Context, language: String?, category: String?, country: String?): LiveData<Resource<List<SourceEntity>>> {
        return object : NetworkBoundResource<List<SourceEntity>, SourceResponse>() {
            override fun onFetchFailed() {
                repoRateLimiter.reset("all")
            }

            override fun saveCallResult(item: SourceResponse) {
//                To avoid this make API response pojo class as entity
                var sourceList = ArrayList<SourceEntity>()
                item.sources.forEach {
                    var sourceEntity = SourceEntity(it.id, it.name!!,it.description!!, it.url,it.category!!,it.language!!,it.country)
                    sourceList.add(sourceEntity)
                }
                NewsDBHelper.getInstance(context).getSourceDao().insertSources(sourceList)
            }

            override fun shouldFetch(data: List<SourceEntity>?): Boolean = repoRateLimiter.shouldFetch("all")

            override fun loadFromDb(): LiveData<List<SourceEntity>> {
                return NewsDBHelper.getInstance(context).getSourceDao().getAllNewsSource()
            }

            override fun createCall(): LiveData<ApiResponse<SourceResponse>> {
                return apiInterface.getSources(language, category, country)
            }
        }.asLiveData()
    }

    fun getNewsArticles(source: String, sortBy: String?): LiveData<ArticlesResponse> {
        val liveDataArticlesResponse: MutableLiveData<ArticlesResponse> = MutableLiveData()
        apiInterface.getArticles(source, sortBy, "20ada10814aa4670bcfc66a18207b72e").enqueue(object : Callback<ArticlesResponse> {
            override fun onFailure(call: Call<ArticlesResponse>?, t: Throwable?) {
                Log.e("Oops", "Network error ${t?.message}")
            }

            override fun onResponse(call: Call<ArticlesResponse>, response: Response<ArticlesResponse>) {
                Log.e("Article Call Status", response.body()?.status ?: "null")
                Log.e("Article Call List", "${response.body()?.articles?.size ?: 0 }")
                liveDataArticlesResponse.value = response.body()
            }
        })
        return liveDataArticlesResponse
    }
}