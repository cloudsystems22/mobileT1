package com.avanade.mobilet1.viewmodels

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.repositories.AuthRepository
import com.avanade.mobilet1.views.HomeActivity

class AuthViewModel(application: Application)
    : ViewModel() {

    var authRepository = AuthRepository(application)


    fun register(userName:String, password:String) = authRepository.register(userName, password)

    fun login(userName:String, password:String) = authRepository.login(userName, password)

}