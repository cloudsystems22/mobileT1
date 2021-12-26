package com.avanade.mobilet1.entities

import java.util.ArrayList

class Movies(
    var id:String = "",
    var title:String = "",
    var thumb:String = "",
    var poster:String = "",
    //var likes:Int = 0,
    var like:ArrayList<String> = ArrayList(),
    var sinopse:String = "",
    var author:String = "",
    var year:String = "",
    var category:String = "",
    var userId:String = ""
)