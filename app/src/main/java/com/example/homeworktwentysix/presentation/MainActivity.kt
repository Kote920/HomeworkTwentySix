package com.example.homeworktwentysix.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homeworktwentysix.R
import com.example.homeworktwentysix.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}