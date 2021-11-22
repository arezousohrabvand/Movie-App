package com.example.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.assignmenttwo.databinding.ActivityMovieListBinding
import com.example.assignmenttwo.databinding.ActivityMovieRecyclerBinding

class MovieRecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMovieRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel:MovieListViewModel by viewModels()
        viewModel.getMovies().observe( this, {movies->
            var movieAdapter = MovieAdapter(this, movies)
            binding.movieRecyclerView.adapter = movieAdapter

        })

        }




}