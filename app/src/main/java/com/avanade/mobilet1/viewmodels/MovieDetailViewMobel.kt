package com.avanade.mobilet1.viewmodels


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Movies
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MovieDetailViewMobel: ViewModel() {

    private lateinit var firebaseFirestore: CollectionReference

    private var _movie = MutableLiveData<Movies>()

    var number:Int = 0
    val _likes = MutableLiveData<Int>()
    val _likesString = MutableLiveData<String>()
    val countLikes:LiveData<String>
        get() = _likesString

    init {
        firebaseFirestore = FirebaseFirestore.getInstance().collection("movies")

    }

    internal var getMovie: MutableLiveData<Movies>
        get() { return _movie }
        set(value) { _movie.value }


    fun getLikes(){
        _likes.value = ++ number
        _likesString.value = "${_likes.value} Curtidas"
    }

    fun getId(movieId:String){
        getMovie(movieId)
    }

    private fun getMovie(movieId:String) {
        firebaseFirestore.document(movieId)
            .get()
            .addOnSuccessListener(OnSuccessListener {
                val movie = it.toObject(Movies::class.java)
                _movie.value = movie
                number = movie!!.likes
            })

    }

    fun updateLikes(id:String){
        firebaseFirestore
            .document(id)
            .update("likes", _likes.value)
    }

}