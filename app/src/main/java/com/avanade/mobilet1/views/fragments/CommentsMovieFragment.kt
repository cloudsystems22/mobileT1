package com.avanade.mobilet1.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.R
import com.avanade.mobilet1.adapters.CommentsAdapater
import com.avanade.mobilet1.databinding.FragmentCommentsMovieBinding
import com.avanade.mobilet1.entities.Comments
import com.avanade.mobilet1.viewmodels.CommentMovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso


class CommentsMovieFragment() : BottomSheetDialogFragment() {

    private lateinit var commentsAdapater: CommentsAdapater
    private lateinit var binding: FragmentCommentsMovieBinding
    private lateinit var viewModel: CommentMovieViewModel
    private lateinit var comment: Comments
    var commentId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCommentsMovieBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.get("movieId").toString() //"VOLn4h32trppTQwj27R6"

        viewModel = ViewModelProvider.NewInstanceFactory().create(CommentMovieViewModel::class.java)

        viewModel.getId(movieId!!)

        commentsAdapater = CommentsAdapater()
        binding.rcComment.adapter = commentsAdapater

        commentsAdapater.setOnClickItem {
            binding.etComment.setText(it.comment)
            commentId = it.id.toString()

        }

        viewModel.getComments.observe(this, Observer {
            commentsAdapater.updatelist(it)
        })

        binding.ivSendComment.setOnClickListener {
            comment = Comments(
                id = commentId.toString(),
                comment = binding.etComment.text.toString()
            )
            viewModel.commentAdd(comment, movieId, view.context)
            binding.etComment.text.clear()
            commentId = ""
        }

        viewModel.getUser.observe(viewLifecycleOwner, Observer {
            if(it[0].photofile.isNullOrEmpty()){
                binding.imgPerfil.setImageResource(R.drawable.user)
            } else {
                Picasso.with(context)
                    .load(it[0].photofile)
                    .into(binding.imgPerfil)
            }
        })
    }

}