package com.avanade.mobilet1.entities

import java.util.ArrayList

data class Movies(
    var id:String = "",
    var title:String = "",
    var thumb:String = "",
    var poster:String = "",
    var likes:ArrayList<String> = ArrayList(),
    var commnets:Int = 0,
    var sinopse:String = "",
    var author:String = "",
    var year:String = "",
    var category:String = "",
    var actors:ArrayList<String> = ArrayList(),
    var countries:ArrayList<String> = ArrayList(),
    var gender:ArrayList<String> = ArrayList(),
    var imdb_url:String = "",
    var languages:ArrayList<String> = ArrayList(),
    var runtime:String = "",
    var tagline:String = "",
    var rating:String = "",
    //var metascore:String = "",
    var users_rating:String = "",
    var votes:String = "",
    var userId:String = ""

)