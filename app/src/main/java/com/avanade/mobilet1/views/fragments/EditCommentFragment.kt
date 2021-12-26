package com.avanade.mobilet1.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.avanade.mobilet1.R
import com.avanade.mobilet1.databinding.FragmentEditCommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditCommentFragment : DialogFragment() {

    private lateinit var binding: FragmentEditCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(context, "Olá fragment edit!", Toast.LENGTH_SHORT).show()
        binding = FragmentEditCommentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ivSendComment.setOnClickListener {

        }
    }

}