package com.padcmyanmar.padcx.shared.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Phyoe Sandy Soe Tun
 * on 8/22/2020.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showSnackbar(message: String) {
        Snackbar.make(window.decorView,message, Snackbar.LENGTH_INDEFINITE).show()
    }
}