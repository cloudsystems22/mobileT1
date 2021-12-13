package com.avanade.mobilet1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import kotlinx.android.synthetic.main.item_categories_movies.view.*

class MoviesAdapter():RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MovieHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    )

    override fun onBindViewHolder(holder: MoviesAdapter.MovieHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 30

    class MovieHolder(var view:View) : RecyclerView.ViewHolder(view){
        fun bind(){
            with(itemView){

            }
        }
    }

}