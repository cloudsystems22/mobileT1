package com.avanade.mobilet1.views

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avanade.mobilet1.databinding.ActivityCreateMovieBinding
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.repositories.MovieRepository
import com.google.firebase.auth.FirebaseAuth

const val CODE_IMAGE = 5000

class CreateMovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateMovieBinding

    private var fotoMovie: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateMovieBinding.inflate(layoutInflater)
        val view= binding.root

        setContentView(view)

        binding.btnCreateMovie.setOnClickListener {
            gravarMovie()
        }

        binding.cardViewMovieFoto.setOnClickListener {
            abrirGaleria()
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Escolha uma foto"), CODE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, image: Intent?) {
        super.onActivityResult(requestCode, resultCode, image)

        if (requestCode == CODE_IMAGE && resultCode == RESULT_OK) {
            val fluxoImagem = contentResolver.openInputStream(image!!.data!!)
            fotoMovie = BitmapFactory.decodeStream(fluxoImagem)
            binding.ivFilmeFoto.setImageBitmap(fotoMovie)
        }
    }

    private fun gravarMovie() {
        val uid = FirebaseAuth.getInstance().uid

        val movie = Movies(
            title = binding.etMovieName.toString(),
            category = binding.etGenero.toString(),
            year = binding.etYear.toString(),
            sinopse = binding.etSinopse.toString(),
            thumb = binding.etLinkIMDB.toString()
        )

        val repository = MovieRepository()
        repository.gravar(movie, fotoMovie!!, this)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Filme cadastrado com sucesso.")
        builder.setMessage("Seu filme foi gravado com sucesso!!")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}