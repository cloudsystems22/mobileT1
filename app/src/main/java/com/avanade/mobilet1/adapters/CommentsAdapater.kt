package com.avanade.mobilet1.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.databinding.FragmentEditCommentBinding
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.entities.Movies
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseFiretore
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseUser
import com.avanade.mobilet1.views.fragments.CommentsMovieFragment
import com.avanade.mobilet1.views.fragments.EditCommentFragment
import com.google.android.gms.tasks.OnSuccessListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.activity_movie_detail.view.image_movie
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.user_comment.view.*
import kotlin.coroutines.coroutineContext

class CommentsAdapater(): RecyclerView.Adapter<CommentsAdapater.CommentsHolder>() {
    private var comments = emptyList<Comments>()

    private var onClickItem: ((Comments) -> Unit)? = null

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

    fun setOnClickItem(callback: (Comments) -> Unit){
        this.onClickItem = callback
    }

    override fun onBindViewHolder(holder: CommentsAdapater.CommentsHolder, position: Int) {
        holder.bind(comments[position])

        val commentId = comments[position].id

        val editComment = holder.itemView.findViewById<ImageView>(R.id.edit_comment)
        val deletComment = holder.itemView.findViewById<ImageView>(R.id.delete_comment)

        holder.itemView.setOnClickListener {  }

        editComment.setOnClickListener {
            onClickItem?.invoke(comments[position])
        }

        deletComment.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Apagar")
            builder.setMessage("Gostaria de apagar seu comentário?")
            builder.setPositiveButton("Confirmar", DialogInterface.OnClickListener { _, _ ->
                firebaseFiretore.collection("comments")
                    .document(commentId)
                    .delete()
                    .addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(holder.itemView.context, "Comentário apagado com sucesso!", Toast.LENGTH_SHORT).show()
                    })
            })
            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            builder.create().show()
        }

    }

    override fun getItemCount(): Int = comments.size

    class CommentsHolder(var view: View) : RecyclerView.ViewHolder(view){
        lateinit var bitmap: Bitmap

        val userId = firebaseUser!!.uid

        fun bind(comment: Comments){
            with(itemView){
                rc_comment_name.text = comment.username
                rc_comment.text = comment.comment

                if(comment.userId.equals(userId)){
                    lnl_menu.setVisibility(View.VISIBLE)
                } else {
                    lnl_menu.setVisibility(View.INVISIBLE)
                }

                if(comment.photoperfil.isNullOrEmpty()){
                    rc_profile_logo.setImageResource(R.drawable.user)
                } else {
                    Picasso.with(context)
                        .load(comment.photoperfil)
                        .into(rc_profile_logo)
                }

            }
        }
    }
}