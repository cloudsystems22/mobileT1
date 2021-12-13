package com.avanade.mobilet1.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Movie
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categories_movies.view.*
import kotlinx.android.synthetic.main.item_categories_movies.view.title_movie
import kotlinx.android.synthetic.main.item_movie.view.*
import java.io.InputStream
import java.net.URL

class MoviesAdapter(
    private val movies: List<Movies>
):RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MovieHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: MoviesAdapter.MovieHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieHolder(var view:View) : RecyclerView.ViewHolder(view){
        lateinit var bitmap:Bitmap

        fun bind(movies: Movies){
            with(itemView){
                title_movie.text = movies.nome
                text_like.text = movies.likes.toString()
                text_commit.text = movies.comment.toString()

                //var display:String = "https://m.media-amazon.com/images/M/MV5BYzUzOTA5ZTMtMTdlZS00MmQ5LWFmNjEtMjE5MTczN2RjNjE3XkEyXkFqcGdeQXVyNTc2ODIyMzY@._V1_SX300.jpg"
                if(movies.img.isEmpty()){
                    image_movie.setImageResource(R.drawable.naoencontrada)
                } else {
                    Picasso.with(context)
                        .load(movies.img)
                        .into(image_movie)
                }


            }
        }
    }

}