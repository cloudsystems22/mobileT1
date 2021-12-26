package com.avanade.mobilet1.views

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity.apply
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.R
import com.avanade.mobilet1.databinding.ActivityMovieDetailBinding
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.viewmodels.MovieDetailViewMobel
import com.avanade.mobilet1.views.fragments.CommentsMovieFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.io.ByteArrayOutputStream
import java.lang.Integer.parseInt

class MovieDetailActivity : AppCompatActivity() {

    lateinit var viewModel: MovieDetailViewMobel
    lateinit var binding: ActivityMovieDetailBinding

    lateinit var imgPoster:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val movieId = intent.getStringExtra("id")

        viewModel = ViewModelProvider.NewInstanceFactory().create(MovieDetailViewMobel::class.java)
        viewModel.getId(movieId!!)

        viewModel.getMovie.observe(this, Observer {
            binding.tvTitle.text = it.title
            binding.tvGenre.text = " • ${it.category} • "
            binding.tvSinopse.text = it.sinopse
            binding.tvYear.text = it.year
            binding.tvCreatedBy.text = "Criado por: ${it.author}"
            binding.textLike.text = "${it.like.count()} Curtidas"
            Log.i("xpto", "${viewModel.userId}")
            if(it.like.contains(viewModel.userId)){
                binding.imgLike.setImageResource(R.drawable.icolike)
            } else {
                binding.imgLike.setImageResource(R.drawable.likevz)
            }



            binding.textCommit.text = "0 Comentários"

            viewModel._likes.value = it.like.count()
            imgPoster = it.poster
            Picasso.with(this)
                .load(it.poster)
                .into(binding.imageMovie)
        })

        if(binding.tvComment.text == "sem_comentarios"){
            binding.commentAtual.setVisibility(View.INVISIBLE)
            binding.firstComment.setVisibility(View.VISIBLE)
        }

        viewModel.getComments.observe(this, Observer{
            binding.commentAtual.setVisibility(View.VISIBLE)
            binding.firstComment.setVisibility(View.INVISIBLE)

            binding.tvUserName.text = it[0].username
            binding.tvComment.text = it[0].comment

            Picasso.with(this)
                .load(it[0].photoperfil)
                .into(binding.imgUser)
        })

        binding.ivBack.setOnClickListener {
            finish()
        }

        //viewModel.number = intent.getIntExtra("likes", 0)
        viewModel.countLikes.observe( this, {
            binding.textLike.text = it.toString()
        })

        binding.imgLike.setOnClickListener {
            viewModel.getLikes()
            viewModel.updateLikes(movieId!!)
        }

        binding.commentAtual.setOnClickListener {
            openBottomSheet(movieId)
        }

        binding.firstComment.setOnClickListener {
            openBottomSheet(movieId)
        }

        binding.shareFile.setOnClickListener {
            val movie = """
                Titulo: ${binding.tvTitle.text}
                Sinópse: ${binding.tvSinopse.text}
                indicado pelo My Movie
            """.trimIndent()
            sharedMovie(movie, imgPoster.toString())
        }

    }

    fun openBottomSheet(movieId:String){
        val args = Bundle()
        args.putString("movieId", movieId)

        var commentsMovieFragment = CommentsMovieFragment()
        commentsMovieFragment.arguments = args
        commentsMovieFragment.show(supportFragmentManager, "TAG")

    }

    fun sharedMovie(movie:String, img:String){

        var shareIntent = Intent(Intent.ACTION_SEND)
        var drawable = image_movie.drawable
        var bitmap = drawable.toBitmap()

        var bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        shareIntent.type = "image/jpg"

        var path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "My Movie", null)

        var uri = Uri.parse(path)

        shareIntent.putExtra(
            Intent.EXTRA_STREAM,
            uri
        )

        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "$movie"
        )
        shareIntent.type = "text/plain"

        var chooser = Intent.createChooser(shareIntent, "Compartilhando")

        startActivity(chooser)
    }
}

