package com.avanade.mobilet1.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.adapters.CommentsAdapater
import com.avanade.mobilet1.databinding.FragmentCommentsMovieBinding
import com.avanade.mobilet1.viewmodels.CommentMovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CommentsMovieFragment() : BottomSheetDialogFragment() {

    private lateinit var commentsAdapater: CommentsAdapater
    private lateinit var binding: FragmentCommentsMovieBinding
    private lateinit var viewModel: CommentMovieViewModel

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

        viewModel.getComments.observe(this, Observer {
            commentsAdapater.updatelist(it)
        })
    }

}