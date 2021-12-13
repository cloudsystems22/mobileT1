package com.avanade.mobilet1.adapters

import com.google.gson.annotations.SerializedName

data class Listcategories(
    val categories: List<Categories>
)

data class Categories(
    val title: String,
    val movies: List<Movies>
)

data class Movies(
    @SerializedName("name") val nome:String,
    @SerializedName("likes") val likes:Int,
    @SerializedName("comment") val comment:Int,
    @SerializedName("img") val img:String,
)