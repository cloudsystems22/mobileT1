package com.avanade.mobilet1.viewmodels


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MovieDetailViewMobel: ViewModel() {

    private lateinit var firebaseFirestore: FirebaseFirestore

    private var _movie = MutableLiveData<Movies>()

    private var _comments = MutableLiveData<ArrayList<Comments>>()

    private var comments = ArrayList<Comments>()

    var number:Int = 0
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

    internal var getComments: MutableLiveData<ArrayList<Comments>>
        get() { return _comments }
        set(value) { _comments.value }

    fun getLikes(){
        _likes.value = ++ number
        _likesString.value = "${_likes.value} Curtidas"
    }

    fun getId(movieId:String){
        getMovie(movieId)
        getComments(movieId)
    }

    private fun getMovie(movieId:String) {
        firebaseFirestore
            .collection("movies")
            .document(movieId)
            .get()
            .addOnSuccessListener(OnSuccessListener {
                val movie = it.toObject(Movies::class.java)
                _movie.value = movie
                number = movie!!.likes
            })

    }

    private fun getComments(movieId:String) {
        firebaseFirestore
            .collection("comments")
            .whereEqualTo("movieId", movieId)
            .orderBy("comment").limit(1)
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                comments = ArrayList<Comments>()
                if(snapshot != null){
                    val documents = snapshot.documents
                    Log.e("xpto", "$documents")

                    documents.forEach {
                        Log.e("xpto", "${it.id}")
                        var comment = it.toObject(Comments::class.java)
                        comment!!.id = it.id
                        comments.add(comment)
                    }
                }
                //println(categories)
                _comments.value = comments

            }
    }

    fun updateLikes(id:String){
        firebaseFirestore
            .collection("movies")
            .document(id)
            .update("likes", _likes.value)
    }

}