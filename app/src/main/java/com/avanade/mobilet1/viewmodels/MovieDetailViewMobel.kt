package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Movies
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MovieDetailViewMobel: ViewModel() {

    private lateinit var firebaseFirestore: FirebaseFirestore

    private lateinit var _movie: MutableLiveData<Movies>
    private var movie = Movies()

    var number:Int = 1
    val _likes = MutableLiveData<Int>()
    val _likesString = MutableLiveData<String>()
    val countLikes:LiveData<String>
    get() = _likesString

    init {
        firebaseFirestore = FirebaseFirestore.getInstance()

    }

    internal var getMovie: MutableLiveData<Movies>
        get() { return _movie }
        set(value) { _movie.value }

    fun getLikes(){
        _likes.value = ++ number
        _likesString.value = "${_likes.value} Curtidas"
    }


    private fun updateMovie() {
        firebaseFirestore.collection("movies")
            .document("")

    }

}