package com.avanade.mobilet1.views.fragments

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.R
import androidx.fragment.app.Fragment
import com.avanade.mobilet1.databinding.FragmentAddMovieBinding
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.repositories.MovieRepository
import com.avanade.mobilet1.views.CODE_IMAGE
import com.google.firebase.auth.FirebaseAuth
const val CODE_IMAGE = 5000

class AddMovieFragment : Fragment() {

    lateinit var binding: FragmentAddMovieBinding

    private var fotoMovie: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun setupSpinner(view: View) {
//        val generos = arrayOf("Ação", "Comédia", "Terror", "Aventura", "Ficção", "Drama", "Infantil")
        //val spinner = binding.etGenero
        val spinner: Spinner = binding.etGenero

        ArrayAdapter.createFromResource(
            view.context,
            com.avanade.mobilet1.R.array.genero_filme,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddMovieBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner(view)

        binding.btnCreateMovie.setOnClickListener {
            gravarMovie(view)
        }

        binding.cardViewMovieFoto.setOnClickListener {
            //abrirGaleria()
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Escolha a imagem do filme"), CODE_IMAGE)
    }


    private fun gravarMovie(view: View) {
        val uid = FirebaseAuth.getInstance().uid

        val movie = Movies(
            title = binding.etMovieName.text.toString(),
            category = binding.etGenero.selectedItem.toString(),
            year = binding.etYear.text.toString(),
            sinopse = binding.etSinopse.text.toString(),
            thumb = binding.etLinkIMDB.text.toString()
        )

        val repository = MovieRepository()
        repository.gravar(movie, view.context)

        val builder = AlertDialog.Builder(view.context)
        builder.setTitle("Filme cadastrado com sucesso.")
        builder.setMessage("Seu filme foi gravado com sucesso!!")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}