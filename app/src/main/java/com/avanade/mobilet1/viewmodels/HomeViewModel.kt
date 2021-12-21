package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Categories
import com.avanade.mobilet1.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel: ViewModel() {

    private var _categories = MutableLiveData<ArrayList<Categories>>()

    private var categories = ArrayList<Categories>()

    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    init{
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        listenerMyMovies()
    }

    internal var getMyMovies: MutableLiveData<ArrayList<Categories>>
        get() { return _categories }
        set(value) { _categories.value }

    private fun listenerMyMovies() {

        val usuarioLogadoId = firebaseAuth.uid

        firebaseFirestore.collection("movies")
            .whereEqualTo("userId",usuarioLogadoId)
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                categories = ArrayList<Categories>()
                if(snapshot != null){
                    val documents = snapshot.documents
                    println(documents)

                    documents.forEach {
                        var categoria = it.toObject(Categories::class.java)
                        categoria!!.id = it.id
                        categories.add(categoria!!)
                    }
                }
                //println(categories)
                _categories.value = categories

            }
    }

}