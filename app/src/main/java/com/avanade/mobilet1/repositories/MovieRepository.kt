package com.avanade.mobilet1.repositories

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.imagens.converterBitmapToByteArray
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class MovieRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance().getReference("posters")

    fun gravar(movie: Movies, bitmap: Bitmap, context: Context) {

        firestore.collection("movies")
            .add(movie)
            .addOnSuccessListener {

                //Gravar a foto no firebase
                var fotoUri = ""
                var movieId = it.id

                var nomeFoto = "$movieId.jpg"

                //criar uma referência para o arquivo
                val fotoMovieRef = storage.child(nomeFoto)

                fotoMovieRef.putBytes(converterBitmapToByteArray(bitmap))
                    .addOnSuccessListener(OnSuccessListener {
                        fotoMovieRef.downloadUrl.addOnSuccessListener(OnSuccessListener {
                            firestore.collection("movies")
                                .document(movieId)
                                .update("poster", it.toString())
                        })
                    })


                Toast.makeText(context, "Filme gravado com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Ocorreu um erro ao gravar!", Toast.LENGTH_SHORT).show()
            }

    }

}