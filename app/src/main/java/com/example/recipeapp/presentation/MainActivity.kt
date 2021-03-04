package com.example.recipeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.R
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       }
}

