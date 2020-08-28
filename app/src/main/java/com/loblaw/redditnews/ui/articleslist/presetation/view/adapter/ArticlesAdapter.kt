package com.loblaw.redditnews.ui.articleslist.presetation.view.adapter

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loblaw.redditnews.R
import com.loblaw.redditnews.base.presentation.view.adapter.BaseRecyclerAdapter
import com.loblaw.redditnews.base.presentation.view.extension.getInflatedView
import com.loblaw.redditnews.base.presentation.view.extension.loadFromUrl
import com.loblaw.redditnews.base.presentation.view.extension.setVisible
import com.loblaw.redditnews.data.remote.network.response.ChildData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_article.view.*


class ArticlesAdapter : BaseRecyclerAdapter<ChildData>() {

    private val mViewClickSubject = PublishSubject.create<ChildData>()

    fun getViewClickedObservable(): Observable<ChildData> {
        return mViewClickSubject
    }

    override fun getAdapterPageSize(): Int {
        return PAGE_SIZE
    }

    override fun mainItemView(parent: ViewGroup): View {
        return parent.getInflatedView(R.layout.item_article)
    }


    override fun mainItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ArticleViewHolder(view)
    }

    override fun onBindMainViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) {
            holder.bind(getItems()[position])
            holder.itemView.setOnClickListener {
                mViewClickSubject.onNext(getItems()[position])
            }
        }
    }

    private class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ChildData) = with(itemView) {
            tvArticleTitle.text = item.article.title
            item.article.thumbnail.let {
                imgArticle.setVisible(it.isNotBlank())
                imgArticle.loadFromUrl(it)
            }
        }
    }
}