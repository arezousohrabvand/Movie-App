package com.example.assignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmenttwo.databinding.ActivityNewMovieBinding
import com.example.assignmenttwo.databinding.ActivityTopMovieBinding

class TopMovieActivity : AppCompatActivity() {
    //setup view binding
    private lateinit var binding: ActivityTopMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityTopMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nameTextView.text="Hello "+intent.getStringExtra("userName")

    }
}