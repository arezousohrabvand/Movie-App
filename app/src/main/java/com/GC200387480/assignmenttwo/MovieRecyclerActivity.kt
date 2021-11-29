package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.GC200387480.assignmenttwo.databinding.ActivityMovieRecyclerBinding
import com.firebase.ui.auth.AuthUI

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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id == R.id.add){
            val startNewMovie = Intent(this, NewMovieActivity::class.java)
            startActivity(startNewMovie )
            Toast.makeText(this,"You choose add page" ,Toast.LENGTH_LONG).show()
            return true
        }
        if(id == R.id.sign_out){
            AuthUI.getInstance().signOut(this)
                .addOnSuccessListener {
                    val intent = Intent(this, SigninActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Successfully Log Out", Toast.LENGTH_SHORT).show()
                }
        }
        if(id == R.id.list){
            val startMovieList = Intent(this, MovieRecyclerActivity::class.java)
            startActivity(startMovieList)
            Toast.makeText(this,"You choose Movie list" ,Toast.LENGTH_LONG).show()
            return true
        }
        if(id == R.id.profile){
            AuthUI.getInstance().signOut(this)
                .addOnSuccessListener {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "You choose Profile page", Toast.LENGTH_SHORT).show()
                }
        }
        if(id == R.id.homepage){
            AuthUI.getInstance().signOut(this)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "You choose Home page", Toast.LENGTH_SHORT).show()
                }
        }
        return super.onOptionsItemSelected(item)
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