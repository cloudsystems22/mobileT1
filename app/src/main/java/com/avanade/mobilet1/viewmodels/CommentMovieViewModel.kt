package com.avanade.mobilet1.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.entities.Users
import com.avanade.mobilet1.utils.FirebaseUtils
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseUser
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class CommentMovieViewModel: ViewModel() {

    private lateinit var firebaseFirestore: CollectionReference

    private var _comments = MutableLiveData<ArrayList<Comments>>()
    private var comments = ArrayList<Comments>()
    private var _users = MutableLiveData<ArrayList<Users>>()
    private var users = ArrayList<Users>()

    init{
        firebaseFirestore = FirebaseFirestore.getInstance().collection("comments")
        getUser(firebaseUser!!.uid)
    }

    internal var getComments: MutableLiveData<ArrayList<Comments>>
        get() { return _comments }
        set(value) { _comments.value }

    internal var getUser: MutableLiveData<ArrayList<Users>>
        get() { return _users }
        set(value) { _users.value }


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

                    documents.forEach {
                        var comment = it.toObject(Comments::class.java)
                        comment!!.id = it.id
                        comments.add(comment)
                    }
                }
                _comments.value = comments

            }
    }

    fun commentAdd(comments: Comments, movieId: String, context: Context){
        comments.userId = firebaseUser!!.uid
        comments.movieId = movieId
        comments.photoperfil = users[0].photofile
        comments.username = firebaseUser!!.email.toString()

        if(comments.id.isNullOrEmpty()){
            firebaseFirestore
                .add(comments)
                .addOnSuccessListener(OnSuccessListener {
                    Toast.makeText(context, "Comentário enviado com sucesso", Toast.LENGTH_LONG).show()

                })
        } else {
            firebaseFirestore
                .document(comments.id)
                .update("comment", comments.comment)
                .addOnSuccessListener(OnSuccessListener {
                    Toast.makeText(context, "Comentário atualizado com sucesso!", Toast.LENGTH_LONG).show()

                })
        }


    }

    fun getUser(uid:String){

        FirebaseUtils.firebaseFiretore.collection("users")
            .whereEqualTo("userId", uid)
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    val documents = snapshot.documents
                    documents.forEach {
                        var user = it.toObject(Users::class.java)
                        user!!.id = it.id
                        users.add(user)
                        Log.e("xpto", "$users")
                    }
                }
                _users.value = users
            }
    }

}