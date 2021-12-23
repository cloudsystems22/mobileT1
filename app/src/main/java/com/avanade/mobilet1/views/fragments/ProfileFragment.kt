package com.avanade.mobilet1.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.avanade.mobilet1.R
import com.avanade.mobilet1.utils.FirebaseUtils.firebaseAuth
import com.avanade.mobilet1.views.CreateAccountActivity
import com.avanade.mobilet1.views.SignInActivity
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    lateinit var btnSignOut:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignOut = view.findViewById<Button>(R.id.btnSignOut)

        btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(view.context,"Sign-Out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(view.context, SignInActivity::class.java))
        }
    }
}