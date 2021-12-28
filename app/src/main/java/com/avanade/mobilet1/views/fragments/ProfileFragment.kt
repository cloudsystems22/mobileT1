package com.avanade.mobilet1.views.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.R
import com.avanade.mobilet1.databinding.FragmentProfileBinding
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseAuth
import com.avanade.mobilet1.viewmodels.UsersViewModel
import com.avanade.mobilet1.views.CreateAccountActivity
import com.avanade.mobilet1.views.EditProfileActivity
import com.avanade.mobilet1.views.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UsersViewModel
    private lateinit var photoFile: String

    lateinit var imagePerfil: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.NewInstanceFactory().create(UsersViewModel::class.java)

        binding.btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(view.context,"Sign-Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(view.context, SignInActivity::class.java))
        }

        photoFile = ""
        viewModel.getUser.observe(viewLifecycleOwner, Observer {
            binding.tvUserName.text = it[0].username
            binding.tvEmail.text = it[0].email
            binding.tvDataNasc.text = it[0].datanascimento

            if(it[0].photofile.isNullOrEmpty()){
                binding.imgPerfil.setImageResource(R.drawable.user)
            } else {
                photoFile = it[0].photofile
                Picasso.with(context)
                    .load(it[0].photofile)
                    .into(binding.imgPerfil)
            }
        })

        binding.btnEditPerfil.setOnClickListener {
            val intent = Intent(context, EditProfileActivity::class.java)
            intent.putExtra("username", binding.tvUserName.text.toString())
            intent.putExtra("photofile", photoFile)
            intent.putExtra("datanascimento", binding.tvDataNasc.text.toString())
            startActivity(intent)
        }

    }

}