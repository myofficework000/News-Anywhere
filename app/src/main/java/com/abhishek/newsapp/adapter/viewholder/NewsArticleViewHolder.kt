package com.abhishek.newsapp.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.abhishek.newsapp.load
import com.abhishek.newsapp.toFormattedString
import com.abhishek.newsapp.ui.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_news_article_single.view.iv_article_image
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_article_description
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_author
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_publish_date
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_title
import java.util.Date

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(article: Article) = with(itemView){
        tv_author.text = article.author
        val date : Date? = null
        tv_publish_date.text = date?.toFormattedString(article.publishedAt!!) ?: article.publishedAt
        tv_title.text = article.title
        tv_article_description.text = article.description
        iv_article_image.load(article.urlToImage!!) { requestCreator -> requestCreator.fit().centerCrop() }
        Picasso.with(itemView.context).load(article.urlToImage).fit().centerCrop().into(iv_article_image)
    }
}