package com.avanade.mobilet1.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avanade.mobilet1.R
import com.avanade.mobilet1.extensions.Extensions.toast
import com.avanade.mobilet1.utils.FirebaseUtils.database
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    //    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val refUsuario = database.getReference("usuarios")
        val refFilme = database.getReference("filmes")

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Referencia das entidades

        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, CreateAccountActivity::class.java))
            toast("signed out")
            finish()
        }
  /*      var filme = Filme()
        var usuario = Usuario()

        val UsuarioPesquisa = refFilme.child("-MqBL7cb1Lelps7CoY7L")


        val filmeListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val filmeDetails = snapshot.getValue().toString()
                Log.i("Dados do Filme", filmeDetails)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        UsuarioPesquisa.addValueEventListener(filmeListener)*/

        /*
               usuario.nome = "Getulio"
               usuario.sobreNome = "Silva"
               usuario.email = "getulio.r.da.silva@avanade.com"

               usuario.nome = "Jessica"
               usuario.sobreNome = "Passos"
               usuario.email = "jazz.passos@gmail.com"

               refUsuario.push().setValue(usuario)
       */

/*        filme.nome = "Um Dia de Fúria"
        filme.sinopse =
            "Um policial tenta deter as atitudes violentas de William Foster, um homem de meia-idade estressado, que está desempregado e em processo de divórcio. Frustrado com o trânsito, o homem fica enfurecido, quando seu carro quebra em um engarrafamento gigante em uma das rodovias da área de Los Angeles."
        filme.genero = Genero.MUSICAL
        filme.dataDeLancamento = "26 de fevereiro de 1993 (EUA)"
        filme.distribuicao = "Warner Bros."
        filme.diretor = "Joel Schumacher"
        filme.roteiro = "Steve Oedekerk"
        filme.producao = "Timothy Harris; Arnold Kopelson; Herschel Weingrod"
        filme.idioma = "inglês"
        filme.premios = ""
        filme.tituloNoBrasil = "Patch Adams - O Amor É Contagioso"
        filme.curiosidades =
            "- Um Dia de Fúria foi rodado durante os distúrbios ocorridos na cidade de Los Angeles, em 1992."

        refFilme.push().setValue(filme)*/
    }
}