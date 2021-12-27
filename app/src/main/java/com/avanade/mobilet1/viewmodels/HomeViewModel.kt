package com.avanade.mobilet1.viewmodels

import android.util.Log
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.repositories.AuthRepository
import com.avanade.mobilet1.utils.FirebaseUtils
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseFiretore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel: ViewModel() {

    private var _movies = MutableLiveData<ArrayList<Movies>>()

    private var movies = ArrayList<Movies>()

    init{
        listenerMyMovies()
    }

    internal var getMyMovies: MutableLiveData<ArrayList<Movies>>
        get() { return _movies }
        set(value) { _movies.value }

    private fun listenerMyMovies() {

        firebaseFiretore.collection("movies")
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
                _movies.value = movies

            }
    }

}