package com.avanade.mobilet1.adapters

import com.google.gson.annotations.SerializedName

data class Listcategories(
    val categories: List<Categories>
)

data class Categories(
    var title: String,
    var movies: List<Movies>
)

data class Movies(
    var nome:String,
    var likes:Int,
    var comment:Int,
    var img:String
    //@SerializedName("name") val nome:String,
    //@SerializedName("likes") val likes:Int,
    //@SerializedName("comment") val comment:Int,
    //@SerializedName("img") val img:String,
)