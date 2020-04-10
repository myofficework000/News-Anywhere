package com.abhishek.newsapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.abhishek.newsapp.R
import com.abhishek.newsapp.adapter.viewholder.NewsArticleViewHolder
import com.abhishek.newsapp.inflate
import com.abhishek.newsapp.ui.model.Article

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class NewsArticleAdapter(private val articles: List<Article>)
    : RecyclerView.Adapter<NewsArticleViewHolder>() {

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int)
            = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder
            = NewsArticleViewHolder(parent.inflate(R.layout.layout_news_article_single))


    override fun getItemCount(): Int = articles.size

    private fun getItem(position: Int): Article = articles[position]

}