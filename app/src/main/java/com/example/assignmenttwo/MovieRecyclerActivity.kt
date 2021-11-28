package com.example.assignmenttwo

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.example.assignmenttwo.databinding.ActivityMovieRecyclerBinding
import java.io.ByteArrayOutputStream

class MovieRecyclerActivity : AppCompatActivity(),MovieAdapter.OnMovieItemClickListener{
    private lateinit var binding: ActivityMovieRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMovieRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel:MovieListViewModel by viewModels()
        viewModel.getMovies().observe( this, {movies->
            var movieAdapter = MovieAdapter(this, movies,this)
            binding.movieRecyclerView.adapter = movieAdapter

        })

        }

    override fun onItemClicked(movie: Movies) {
    Toast.makeText(this,movie.movieName,Toast.LENGTH_LONG).show()
        val intent=Intent(this,ReviewActivity::class.java)
        intent.putExtra("movieID",movie.id)
        intent.putExtra("movieName",movie.movieName)


        startActivity(intent)
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        var menuItem=menu!!.findItem(R.id.searchbar)
        var searchView=menuItem.actionView as SearchView
        searchView.maxWidth= Int.MAX_VALUE

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("TAG","====>$newText")
                return true
            }

        })



        return true
    }*/



}