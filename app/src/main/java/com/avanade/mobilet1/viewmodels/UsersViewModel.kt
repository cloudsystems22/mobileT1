package com.avanade.mobilet1.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.entities.Users
import com.avanade.mobilet1.imagens.converterBitmapToByteArray
import com.avanade.mobilet1.utils.FirebaseUtils
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseUser
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class UsersViewModel: ViewModel() {
    private var _users = MutableLiveData<ArrayList<Users>>()
    private var users = ArrayList<Users>()

    private lateinit var firebaseFirestore: CollectionReference
    private lateinit var userId:String

    init{
        firebaseFirestore = FirebaseFirestore.getInstance().collection("users")
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        getUser(userId)
    }

    internal var getUser: MutableLiveData<ArrayList<Users>>
        get() { return _users }
        set(value) { _users.value }

    fun getUser(uid:String){
        firebaseFirestore
            .whereEqualTo("userId", uid)
            .addSnapshotListener { snapshot, error ->
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

    fun editUser(user:Users){
        firebaseFirestore
            .document(_users.value!![0].id)
            .update(
                "username", user.username,
                "datanascimento", user.datanascimento
            )
    }

    fun editImgPerfil(bitmap: Bitmap){
        var fotoUri = ""
        var userId = _users.value!![0].id

        var nomeFoto = "${userId}.jpg"
        val fotoPerfilRef = FirebaseUtils.firebaseStorage
            .getReference("fotosUsuario")
            .child(nomeFoto)

        fotoPerfilRef.putBytes(converterBitmapToByteArray(bitmap))
            .addOnSuccessListener({
                fotoPerfilRef.downloadUrl.addOnSuccessListener(OnSuccessListener {
                    FirebaseUtils.firebaseFiretore.collection("users")
                        .document(userId)
                        .update("photofile", it.toString())
                })
            })
    }

}