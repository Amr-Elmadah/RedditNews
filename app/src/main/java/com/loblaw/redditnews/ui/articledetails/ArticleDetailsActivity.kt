package com.loblaw.redditnews.ui.articledetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.loblaw.redditnews.R
import com.loblaw.redditnews.data.remote.network.response.Article
import com.loblaw.redditnews.databinding.ActivityArticleDetailsBinding
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var activityArticleDetailsBinding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArticleDetailsBinding = setContentView(this, R.layout.activity_article_details)
        bindArticle()
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val EXTRA_ARTICLE = "article"
    }

    private fun bindArticle() {
        val extras = intent.extras
        extras?.let {
            activityArticleDetailsBinding.article = (it.getParcelable(EXTRA_ARTICLE) as? Article)!!
        }
    }
}