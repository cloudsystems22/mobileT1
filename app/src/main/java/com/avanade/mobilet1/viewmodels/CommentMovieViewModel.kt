package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class CommentMovieViewModel: ViewModel() {

    private lateinit var firebaseFirestore: CollectionReference

    private var _comments = MutableLiveData<ArrayList<Comments>>()

    private var comments = ArrayList<Comments>()

    init{
        firebaseFirestore = FirebaseFirestore.getInstance().collection("comments")
    }

    internal var getComments: MutableLiveData<ArrayList<Comments>>
        get() { return _comments }
        set(value) { _comments.value }

    fun getId(movieId:String){
        getComments(movieId)
    }

    private fun getComments(movieId:String) {
        firebaseFirestore
            .whereEqualTo("movieId", movieId)
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

}