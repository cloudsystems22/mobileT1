package com.avanade.mobilet1.repositories

import com.avanade.mobilet1.extensions.Extensions.toast
import com.avanade.mobilet1.extensions.Extensions.startNewActivity
import com.avanade.mobilet1.utils.FirebaseUtils
import com.avanade.mobilet1.views.CreateAccountActivity
import com.avanade.mobilet1.views.HomeActivity

class AuthRepository(){

    fun register(userName: String, password: String) {
        FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(userName, password)
            .addOnCompleteListener {
               if(it.isSuccessful){
                   toast(CreateAccountActivity::class.java, "")
                   startNewActivity(HomeActivity::class.java)
                   sendEmailVerification()
               } else{
                   toast("failed to Authenticate !")
               }
            }
    }

    fun login(userName: String, password: String) {
        FirebaseUtils.firebaseAuth.signInWithEmailAndPassword(userName, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    toast(CreateAccountActivity::class.java, "")
                    startNewActivity(HomeActivity::class.java)
                } else {
                    toast("failed to Authenticate !")
                }
            }
    }

    private fun sendEmailVerification() {
        FirebaseUtils.firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }
        }
    }

}