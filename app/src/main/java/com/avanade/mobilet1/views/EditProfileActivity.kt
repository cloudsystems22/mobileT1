package com.avanade.mobilet1.views

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.R
import com.avanade.mobilet1.databinding.ActivityEditProfileBinding
import com.avanade.mobilet1.entities.Users
import com.avanade.mobilet1.imagens.converterBitmapToByteArray
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseFiretore
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseStorage
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseUser
import com.avanade.mobilet1.viewmodels.EditProfileViewModel
import com.avanade.mobilet1.viewmodels.UsersViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var viewModel: UsersViewModel

    lateinit var imagePerfil: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider.NewInstanceFactory().create(UsersViewModel::class.java)

        binding.editImag.setOnClickListener {
            abrirGaleria()
        }

        binding.btnEditPerfil.setOnClickListener {
            var user = Users(
                username = binding.etUsername.text.toString(),
                datanascimento = binding.etDatanasc.text.toString(),
            )
            viewModel.editUser(user)
            finish()
        }

        val emailUser = FirebaseAuth.getInstance().currentUser!!.email
        val userName = intent.getStringExtra("username")
        var photoFile = intent.getStringExtra("photofile")
        var dataNascimento = intent.getStringExtra("datanascimento")

        if(photoFile.isNullOrEmpty()){
            binding.imgPerfil.setImageResource(R.drawable.user)
        } else {
            Picasso.with(this)
                .load(photoFile)
                .into(binding.imgPerfil)
        }

        binding.etUsername.setText(userName)
        binding.etEmail.setEnabled(false)
        binding.etEmail.setText(emailUser)
        binding.etDatanasc.setText(dataNascimento)

        binding.ivBack.setOnClickListener {
            finish()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imagem: Intent?) {
        super.onActivityResult(requestCode, resultCode, imagem)

        if(requestCode == CODE_IMAGE && resultCode == -1){
            var fluxoImage = contentResolver.openInputStream(imagem!!.data!!)

            imagePerfil = BitmapFactory.decodeStream(fluxoImage)

            binding.imgPerfil.setImageBitmap(imagePerfil)
        }
        Log.e("Avanade", requestCode.toString())
        viewModel.editImgPerfil(imagePerfil)

    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        // abrir arquivos de imagem
        startActivityForResult(Intent.createChooser(intent, "Escolha uma foto!"), CODE_IMAGE)
    }

}