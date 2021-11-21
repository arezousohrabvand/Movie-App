package com.example.assignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmenttwo.databinding.ActivityMovieListBinding
import com.example.assignmenttwo.databinding.ActivityMovieRecyclerBinding

class MovieRecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMovieRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}