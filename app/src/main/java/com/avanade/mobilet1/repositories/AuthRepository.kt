package com.avanade.mobilet1.repositories

import android.app.Application
import android.content.Intent
import android.widget.Toast
import com.avanade.mobilet1.utils.FirebaseUtils
import com.avanade.mobilet1.views.HomeActivity

class AuthRepository(application: Application){
    val application = application

    fun register(userName: String, password: String) {
        FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(userName, password)
            .addOnCompleteListener {
               if(it.isSuccessful){
                   val intent = Intent(application, HomeActivity::class.java)
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   application.startActivity(intent)
                   sendEmailVerification(userName)
               } else{
                   Toast.makeText(application, "", Toast.LENGTH_SHORT).show()
               }
            }
    }

    fun login(userName: String, password: String) {
        FirebaseUtils.firebaseAuth.signInWithEmailAndPassword(userName, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(application, "Login: ${userName}", Toast.LENGTH_SHORT).show()
                    val intent  = Intent(application, HomeActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    application.startActivity(intent)

                } else {
                    Toast.makeText(application, "failed to Authenticate !", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendEmailVerification(userName: String) {
        FirebaseUtils.firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(application, "email sent to $userName", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}