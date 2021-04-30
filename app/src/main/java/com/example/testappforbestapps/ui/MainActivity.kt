package com.example.testappforbestapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.testappforbestapps.other.AppApplication
import com.example.testappforbestapps.other.AppViewModel
import com.example.testappforbestapps.other.AppViewModelFactory
import com.example.testappforbestapps.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}