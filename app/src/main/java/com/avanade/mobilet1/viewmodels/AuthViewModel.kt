package com.avanade.mobilet1.viewmodels

import androidx.lifecycle.ViewModel
import com.avanade.mobilet1.repositories.AuthRepository
import com.avanade.mobilet1.utils.FirebaseUtils
import com.avanade.mobilet1.extensions.Extensions.startNewActivity
import com.avanade.mobilet1.views.CreateAccountActivity
import com.avanade.mobilet1.views.HomeActivity


class AuthViewModel()
    : ViewModel() {

    private val authRepository = AuthRepository()

    fun register(userName:String, password:String) = authRepository.register(userName, password)

    fun login(userName:String, password:String) = authRepository.login(userName, password)
}