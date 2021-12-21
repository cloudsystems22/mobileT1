package com.avanade.mobilet1.adapters

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.views.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_categories_movies.view.title_movie
import kotlinx.android.synthetic.main.item_movie.view.*

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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intent.putExtra("id", movies[position].id)
            intent.putExtra("title", movies[position].title)
            intent.putExtra("category", movies[position].category)
            intent.putExtra("sinopse", movies[position].sinopse)
            intent.putExtra("year", movies[position].year)
            intent.putExtra("author", movies[position].author)
            intent.putExtra("poster", movies[position].poster)
            intent.putExtra("likes", movies[position].likes)
            intent.putExtra("comment", movies[position].comment)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = movies.size

    class MovieHolder(var view:View) : RecyclerView.ViewHolder(view){
        lateinit var bitmap:Bitmap

        fun bind(movies: Movies){

            with(itemView){
                title_movie.text = movies.title
                text_like.text = movies.likes.toString()
                text_commit.text = movies.comment.toString()

                //var display:String = "https://firebasestorage.googleapis.com/v0/b/mobiletone.appspot.com/o/posters%2Fthumb%2Fvenom.jpg?alt=media&token=1b29cccb-ba33-4044-b9ca-bab615a44d75"
                if(movies.thumb.isNullOrEmpty()){
                    image_movie.setImageResource(R.drawable.naoencontrada)
                } else {
                    Picasso.with(context)
                        .load(movies.thumb)
                        .into(image_movie)
                }

            }
        }
    }

}