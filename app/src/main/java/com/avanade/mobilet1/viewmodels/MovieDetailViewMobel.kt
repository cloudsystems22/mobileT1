package com.avanade.mobilet1.viewmodels


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MovieDetailViewMobel: ViewModel() {

    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    private var _movie = MutableLiveData<Movies>()
    private var arrayLikes = emptyList<String>()
    internal var userId = ""
    private var _comments = MutableLiveData<ArrayList<Comments>>()
    private var comments = ArrayList<Comments>()


    init {
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        userId = firebaseAuth.currentUser!!.uid

    }

    internal var getMovie: MutableLiveData<Movies>
        get() { return _movie }
        set(value) { _movie.value }

    internal var getComments: MutableLiveData<ArrayList<Comments>>
        get() { return _comments }
        set(value) { _comments.value }


    fun getId(movieId:String){
        getMovie(movieId)
        getComments(movieId)
    }

    private fun getMovie(movieId:String) {
        firebaseFirestore
            .collection("movies")
            .document(movieId)
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    val movie = snapshot.toObject(Movies::class.java)
                    _movie.value = movie
                }
            }

    }

    private fun getComments(movieId:String) {
        firebaseFirestore
            .collection("comments")
            .whereEqualTo("movieId", movieId)
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                comments = ArrayList<Comments>()
                if(snapshot != null){
                    val documents = snapshot.documents
                    //Log.e("xpto", "$documents")

                    documents.forEach {
                        //Log.e("xpto", "${it.id}")
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

        arrayLikes = _movie.value!!.likes

        if(arrayLikes.contains(userId)){
            firebaseFirestore
                .collection("movies")
                .document(id)
                .update("likes", FieldValue.arrayRemove(userId))

        } else {
            firebaseFirestore
                .collection("movies")
                .document(id)
                .update("likes", FieldValue.arrayUnion(userId))

        }

    }

}