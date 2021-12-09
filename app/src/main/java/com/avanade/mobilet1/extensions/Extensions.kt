package com.avanade.mobilet1.extensions

import android.app.Activity
import android.content.Intent
import android.widget.Toast

object Extensions {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun<A: Activity> Activity.startNewActivity(activity: Class<A>){
        Intent(this,activity).also{
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(it)
        }
    }
}