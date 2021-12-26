package com.avanade.mobilet1.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseUser
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
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

    fun commentAdd(comments: Comments, movieId: String, context: Context){
        comments.userId = firebaseUser!!.uid
        comments.movieId = movieId
        comments.username = firebaseUser!!.email.toString()

        firebaseFirestore
            .add(comments)
            .addOnSuccessListener(OnSuccessListener {
                Toast.makeText(context, "Comentário enviado com sucesso", Toast.LENGTH_LONG).show()

            })
    }

    fun editComment(comment:String, id:String){
        firebaseFirestore
            .document(id)
            .update("comment", comment)
    }

}