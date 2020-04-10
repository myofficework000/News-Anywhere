package com.abhishek.newsapp.ui

import android.os.Bundle
import com.abhishek.newsapp.R

/**
 * Created by abhishek.pathak on 08/04/2020.
 */
class HomeActivity : BaseActivity() {

    private var newsFragment : NewsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showSourceFragment()
    }

    private fun showSourceFragment() {
        newsFragment = NewsFragment()
        addFragment(newsFragment!!, R.id.container, "NewsFragment")
    }

    override fun onBackPressed() {
        if (!newsFragment?.onBackPressed()!!)
            super.onBackPressed()
    }
}