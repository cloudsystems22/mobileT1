package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel: ViewModel() {

    private var _movies = MutableLiveData<ArrayList<Movies>>()

    private var movies = ArrayList<Movies>()

    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    init{
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        listenerMyMovies()
    }

    internal var getMyMovies: MutableLiveData<ArrayList<Movies>>
        get() { return _movies }
        set(value) { _movies.value }

    private fun listenerMyMovies() {

        val usuarioLogadoId = firebaseAuth.uid

        firebaseFirestore.collection("movies")
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                movies = ArrayList<Movies>()
                if(snapshot != null){
                    val documents = snapshot.documents
                    println(documents)

                    documents.forEach {
                        var movie = it.toObject(Movies::class.java)
                        movie!!.id = it.id
                        movies.add(movie)
                    }
                }
                //println(categories)
                _movies.value = movies

            }
    }

}