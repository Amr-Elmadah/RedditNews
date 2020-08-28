package com.loblaw.redditnews.data.remote.network.response

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.loblaw.redditnews.base.presentation.view.extension.loadFromUrl
import kotlinx.android.parcel.Parcelize

data class NewsResponse(
    @SerializedName("data")
    var data: Data
)

data class Data(
    @SerializedName("children")
    var articles: List<ChildData>
)

data class ChildData(
    @SerializedName("data")
    var article: Article
)

@Parcelize
data class Article(
    @SerializedName("title")
    var title: String,
    @SerializedName("selftext")
    var body: String,
    @SerializedName("thumbnail")
    var thumbnail: String
) : Parcelable

@BindingAdapter("bind:backgroundImageUrl")
fun loadBackgroundImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let { view.loadFromUrl(it) }
}