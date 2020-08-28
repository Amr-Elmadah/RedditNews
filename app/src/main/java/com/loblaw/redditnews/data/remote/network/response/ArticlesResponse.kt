package com.loblaw.redditnews.data.remote.network.response

import com.google.gson.annotations.SerializedName

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

data class Article(
    @SerializedName("title")
    var title: String,
    @SerializedName("selftext")
    var body: String,
    @SerializedName("thumbnail")
    var thumbnail: String
)