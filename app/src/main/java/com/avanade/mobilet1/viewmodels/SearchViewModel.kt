package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.entities.Users
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseFiretore
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseUser
import com.google.firebase.auth.FirebaseAuth

class SearchViewModel: ViewModel() {

    private var _movies = MutableLiveData<ArrayList<Movies>>()

    private var movies = ArrayList<Movies>()

    private var _users = MutableLiveData<ArrayList<Users>>()

    private var users = ArrayList<Users>()

    private lateinit var userId:String

    init{
        listenerMovies()
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        getUser(userId)
    }

    internal var getMovies: MutableLiveData<ArrayList<Movies>>
        get() { return _movies }
        set(value) { _movies.value }

    internal var getUser: MutableLiveData<ArrayList<Users>>
        get() { return _users }
        set(value) { _users.value }

    private fun listenerMovies() {
        firebaseFiretore.collection("movies")
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                movies = ArrayList<Movies>()
                if(snapshot != null){
                    val documents = snapshot.documents

                    documents.forEach {
                        var movie = it.toObject(Movies::class.java)
                        movie!!.id = it.id
                        movies.add(movie)
                        Log.e("xpto", "$movie")
                    }
                }
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

    fun getUser(uid:String){

        firebaseFiretore.collection("users")
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