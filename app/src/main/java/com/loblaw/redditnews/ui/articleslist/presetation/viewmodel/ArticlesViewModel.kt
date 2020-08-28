package com.loblaw.redditnews.ui.articleslist.presetation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.loblaw.redditnews.base.domain.exception.LobLawNewsException
import com.loblaw.redditnews.base.presentation.model.ObservableResource
import com.loblaw.redditnews.base.presentation.viewmodel.BaseViewModel
import com.loblaw.redditnews.data.remote.network.response.Article
import com.loblaw.redditnews.data.remote.network.response.ChildData
import com.loblaw.redditnews.ui.articleslist.domain.interactor.GetArticlesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel() {
    private var articlesList = mutableListOf<ChildData>()
    val mArticles = MutableLiveData<List<ChildData>>()
    val mAArticlesObservable = ObservableResource<String>()

    fun getArticles() {
        addDisposable(getArticlesUseCase.build(params = "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mAArticlesObservable.loading.postValue(true)
            }
            .doAfterTerminate {
                mAArticlesObservable.loading.postValue(false)
            }
            .subscribe({
                it?.data?.articles?.let {
                    if (it.isNotEmpty()) {
                        articlesList = it.toMutableList()
                        mArticles.value = articlesList
                    }
                }
            }, {
                (it as? LobLawNewsException).let {
                    mAArticlesObservable.error.value = it
                }
            })
        )
    }
}