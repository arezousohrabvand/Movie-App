package com.example.assignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmenttwo.databinding.ActivityMovieReviewBinding

class MovieReviewActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityMovieReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        ActivityMovieReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}