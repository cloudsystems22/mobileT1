package com.avanade.mobilet1.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Categories
import com.avanade.mobilet1.adapters.CategoriesAdapter
import com.avanade.mobilet1.adapters.Listcategories
import com.avanade.mobilet1.adapters.listCategories
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class SearchViewModel: ViewModel() {

    private var _categories = MutableLiveData<ArrayList<Categories>>()
    val searchEdit = MutableLiveData<String>()

    private var categories = ArrayList<Categories>()

    private lateinit var firebaseFirestore: FirebaseFirestore

    init{
        firebaseFirestore = FirebaseFirestore.getInstance()
        listenerMovies()

        searchEdit.value = ""
    }

    internal var getCategories: MutableLiveData<ArrayList<Categories>>
        get() { return _categories }
        set(value) { _categories.value }

    private fun listenerMovies() {
        firebaseFirestore.collection("movies")
            .addSnapshotListener{ snapshot, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                categories = ArrayList<Categories>()
                if(snapshot != null){
                    val documents = snapshot.documents
                    //Log.e("xpto", "$documents")

                    documents.forEach {
                        //Log.e("xpto", "${it.id}")
                        var categoria = it.toObject(Categories::class.java)
                        categoria!!.id = it.id
                        categories.add(categoria)
                    }
                }
                //println(categories)

                _categories.value = categories

            }
    }

    fun filterList(search:String) {
        val newList = ArrayList<Categories>()
        val newCategories = categories.filter { it.name.contains(search) }
        newCategories.forEach{
            newList.add(it)
        }
        _categories.value = newList
    }
}