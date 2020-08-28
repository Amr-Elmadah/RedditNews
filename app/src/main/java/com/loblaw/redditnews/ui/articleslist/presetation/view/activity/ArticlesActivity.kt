package com.loblaw.redditnews.ui.articleslist.presetation.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.loblaw.redditnews.R
import com.loblaw.redditnews.base.presentation.view.extension.showSnack
import com.loblaw.redditnews.base.presentation.viewmodel.ViewModelFactory
import com.loblaw.redditnews.data.remote.network.response.Article
import com.loblaw.redditnews.ui.articledetails.ArticleDetailsActivity
import com.loblaw.redditnews.ui.articleslist.presetation.view.adapter.ArticlesAdapter
import com.loblaw.redditnews.ui.articleslist.presetation.viewmodel.ArticlesViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_articles.*
import javax.inject.Inject

class ArticlesActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ArticlesViewModel>

    private val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ArticlesViewModel::class.java)
    }

    @Inject
    lateinit var manager: LinearLayoutManager

    @Inject
    lateinit var adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_articles)
        setupControllers()
        getArticles()
    }

    private fun setupControllers() {
        setupToolbar()
        setupRecyclerView()
        observeArticlesChange()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun getArticles() {
        mViewModel.getArticles()
    }

    private fun setupRecyclerView() {
        manager.orientation = RecyclerView.VERTICAL
        rvArticles.layoutManager = manager
        rvArticles.adapter = adapter
        srlArticles.setOnRefreshListener { mViewModel.getArticles() }
    }

    @SuppressLint("CheckResult")
    private fun observeArticlesChange() {
        mViewModel.mArticles.observe(this, { articles ->
            articles?.let {
                srlArticles.isRefreshing = false
                adapter.replaceAllItems(it.toMutableList())
            }
        })
        mViewModel.mAArticlesObservable.observe(this,
            successObserver = {
                it?.let {
                    srlArticles.isRefreshing = false
                    llMainContent.showSnack(it)
                }
            }, commonErrorObserver = {
                srlArticles.isRefreshing = false
            }, loadingObserver = {
                it?.let {
                    srlArticles.isRefreshing = it
                }
            }, networkErrorConsumer = {
                srlArticles.isRefreshing = false
                llMainContent.showSnack(
                    getString(R.string.internet_connection),
                    Snackbar.LENGTH_LONG
                )
            })

        adapter.getViewClickedObservable().subscribe {
            it?.let {
                openArticleDetailsActivity(it.article)
            }
        }
    }

    private fun openArticleDetailsActivity(article: Article) {
        val intent = Intent(this, ArticleDetailsActivity::class.java)
        intent.putExtra(ArticleDetailsActivity.EXTRA_ARTICLE, article)
        startActivity(intent)
    }
}
