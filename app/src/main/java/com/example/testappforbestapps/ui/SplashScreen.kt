package com.example.testappforbestapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.testappforbestapps.R
import com.example.testappforbestapps.other.AppApplication
import com.example.testappforbestapps.other.AppViewModel
import com.example.testappforbestapps.other.AppViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels {
        AppViewModelFactory((application as AppApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        supportActionBar?.hide()


        CoroutineScope(Dispatchers.Default).launch{
            for (item in viewModel.loadGsonData()) {
                viewModel.insert(item)
            }
            var intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}