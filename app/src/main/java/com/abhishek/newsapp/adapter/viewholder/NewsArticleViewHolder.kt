package com.abhishek.newsapp.adapter.viewholder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.abhishek.newsapp.load
import com.abhishek.newsapp.toFormattedString
import com.abhishek.newsapp.ui.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_news_article_single.view.cardView
import kotlinx.android.synthetic.main.layout_news_article_single.view.iv_article_image
import kotlinx.android.synthetic.main.layout_news_article_single.view.progressBar
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_article_description
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_link
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_publish_date
import kotlinx.android.synthetic.main.layout_news_article_single.view.tv_title
import kotlinx.android.synthetic.main.layout_news_article_single.view.webViewContent
import android.webkit.WebViewClient




/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class NewsArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetJavaScriptEnabled")
    fun bind(article: Article) = with(itemView) {
        tv_link.setOnClickListener {
            cardView.visibility = View.GONE
            webViewContent.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            webViewContent.getSettings().javaScriptEnabled = true
            webViewContent.webViewClient = WebViewClient()
            webViewContent.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, progress: Int) {
                    progressBar.progress = progress * 100
                }
            }
            webViewContent.loadUrl(article.url)
            webViewContent.isHorizontalScrollBarEnabled = false
        }
        tv_publish_date.text = toFormattedString(article.publishedAt!!) ?: article.publishedAt
        tv_title.text = article.title
        tv_article_description.text = article.description
        iv_article_image.load(article.urlToImage!!) { requestCreator ->
            requestCreator.fit()
                .centerCrop()
        }
        Picasso.with(itemView.context).load(article.urlToImage).fit().centerCrop()
            .into(iv_article_image)
    }
}