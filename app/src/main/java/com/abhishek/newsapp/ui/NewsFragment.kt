package com.abhishek.newsapp.ui

import android.app.ProgressDialog
import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhishek.newsapp.R
import com.abhishek.newsapp.adapter.NewsArticleAdapter
import com.abhishek.newsapp.adapter.NewsSourceAdapter
import com.abhishek.newsapp.api.Resource
import com.abhishek.newsapp.db.SourceEntity
import com.abhishek.newsapp.ui.model.ArticlesResponse
import com.abhishek.newsapp.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.recyclerView

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class NewsFragment : LifecycleFragment(), (SourceEntity) -> Unit {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var observerNewsSource: Observer<Resource<List<SourceEntity>>>
    private lateinit var observerNewsArticle: Observer<ArticlesResponse>
    private lateinit var newsSourceAdapter: NewsSourceAdapter
    private lateinit var newsArticleAdapter: NewsArticleAdapter
    private val sourceList = ArrayList<SourceEntity>()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_news, container, false)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        progressDialog =
            ProgressDialog.show(activity, "News API", "Loading News Source from Web-Service")
        progressDialog.show()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsSourceAdapter = NewsSourceAdapter(this, sourceList)
        recyclerView.adapter = newsSourceAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        observerNewsSource = Observer { newsSource ->
            if (newsSource?.data != null && newsSource.data.isNotEmpty()) {
                progressDialog.dismiss()
                newsSourceAdapter.updateDataSet(newsSource.data)
            }
        }

        observerNewsArticle = Observer { newsArticle ->
            if (newsArticle != null) {
                newsArticleAdapter = NewsArticleAdapter(newsArticle.articles!!)
                recyclerView.layoutManager = LinearLayoutManager(
                    activity, LinearLayoutManager.HORIZONTAL,
                    false
                )
                recyclerView.adapter = newsArticleAdapter
            }
        }


        newsViewModel.getNewsSource(null, null, null)
            .observe(this, observerNewsSource)
    }

    override fun invoke(source: SourceEntity) {
        newsViewModel.getNewsArticles(source.id!!, null)
            .observe(this, observerNewsArticle)
    }

    fun onBackPressed(): Boolean {
        return when {
            recyclerView.adapter is NewsArticleAdapter -> {
                recyclerView.layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = newsSourceAdapter
                true
            }
            else -> false
        }
    }
}