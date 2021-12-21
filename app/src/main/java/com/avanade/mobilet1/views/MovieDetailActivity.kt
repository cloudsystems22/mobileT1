package com.avanade.mobilet1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.R
import com.avanade.mobilet1.databinding.ActivityMovieDetailBinding
import com.avanade.mobilet1.viewmodels.MovieDetailViewMobel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import java.lang.Integer.parseInt

class MovieDetailActivity : AppCompatActivity() {

    lateinit var viewModel: MovieDetailViewMobel
    lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider.NewInstanceFactory().create(MovieDetailViewMobel::class.java)

        binding.tvTitle.text = intent.getStringExtra("title")
        binding.tvGenre.text = " • ${intent.getStringExtra("category")} • "
        binding.tvSinopse.text = intent.getStringExtra("sinopse")
        binding.tvYear.text = intent.getStringExtra("year")
        binding.tvCreatedBy.text = "Criado por: ${intent.getStringExtra("author")}"
        binding.textLike.text = "${intent.getIntExtra("likes", 0)} Curtidas"
        binding.textCommit.text = "${intent.getIntExtra("comment", 0)} Comentários"

        Picasso.with(this)
            .load(intent.getStringExtra("poster"))
            .into(binding.imageMovie)

        binding.ivBack.setOnClickListener {
            finish()
        }

        viewModel.number = intent.getIntExtra("likes", 0)
        viewModel.countLikes.observe( this, {
            binding.textLike.text = it.toString()
        })


        binding.imgLike.setOnClickListener {
            viewModel.getLikes()
        }



    }
}

