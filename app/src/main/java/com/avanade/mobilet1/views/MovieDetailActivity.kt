package com.avanade.mobilet1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.avanade.mobilet1.R

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var intentMovieId = intent.getStringExtra("id")

        var movieId = findViewById<TextView>(R.id.movie_id)

        movieId.setText(intentMovieId)


        Log.e("xpto", "$movieId")
    }
}