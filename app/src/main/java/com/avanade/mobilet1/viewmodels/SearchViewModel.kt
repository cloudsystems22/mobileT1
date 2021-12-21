package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.adapters.CategoriesAdapter
import com.avanade.mobilet1.adapters.Listcategories
import com.avanade.mobilet1.adapters.listCategories
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class SearchViewModel: ViewModel() {

    private var _movies = MutableLiveData<ArrayList<Movies>>()
    val searchEdit = MutableLiveData<String>()

    private var movies = ArrayList<Movies>()

    private lateinit var firebaseFirestore: FirebaseFirestore

    init{
        firebaseFirestore = FirebaseFirestore.getInstance()
        listenerMovies()

        searchEdit.value = ""
    }

    internal var getMovies: MutableLiveData<ArrayList<Movies>>
        get() { return _movies }
        set(value) { _movies.value }

    private fun listenerMovies() {
        firebaseFirestore.collection("movies")
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                movies = ArrayList<Movies>()
                if(snapshot != null){
                    val documents = snapshot.documents
                    //Log.e("xpto", "$documents")

                    documents.forEach {
                        //Log.e("xpto", "${it.id}")
                        var movie = it.toObject(Movies::class.java)
                        movie!!.id = it.id
                        movies.add(movie)
                        Log.e("xpto", "$movie")
                    }
                }
                //println(categories)

                _movies.value = movies

            }
    }

    fun filterList(search:String) {
        val newList = ArrayList<Movies>()
        val newCategories = movies.filter { it.title.contains(search) }
        newCategories.forEach{
            newList.add(it)
        }
        _movies.value = newList
    }
}