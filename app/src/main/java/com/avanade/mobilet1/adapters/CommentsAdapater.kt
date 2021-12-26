package com.avanade.mobilet1.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.activity_movie_detail.view.image_movie
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.user_comment.view.*

class CommentsAdapater(): RecyclerView.Adapter<CommentsAdapater.CommentsHolder>() {
    private var comments = emptyList<Comments>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CommentsHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_comment, parent, false)
    )

    fun updatelist(listComments: MutableList<Comments>){
        comments = listComments
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentsAdapater.CommentsHolder, position: Int) {
        holder.bind(comments[position])

    }

    override fun getItemCount(): Int = comments.size

    class CommentsHolder(var view: View) : RecyclerView.ViewHolder(view){
        lateinit var bitmap: Bitmap

        fun bind(comment: Comments){
            with(itemView){
                rc_comment_name.text = comment.username
                rc_comment.text = comment.comment

                if(comment.photoperfil.isNullOrEmpty()){
                    //image_movie.setImageResource(R.drawable.naoencontrada)
                } else {
                    Picasso.with(context)
                        .load(comment.photoperfil)
                        .into(rc_profile_logo)
                }

            }
        }
    }
}