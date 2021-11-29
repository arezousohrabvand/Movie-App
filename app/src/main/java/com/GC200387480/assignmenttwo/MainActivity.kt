package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.GC200387480.assignmenttwo.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI

class MainActivity : AppCompatActivity() {
    //setup view binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newMovieBtn.setOnClickListener{
            var intent= Intent(this,MovieRecyclerActivity::class.java)
            startActivity(intent)

        }
        binding.topMovieBtn.setOnClickListener{

                var intent= Intent(this,NewMovieActivity::class.java)
                startActivity(intent)



        }


        binding.logoutBtn.setOnClickListener {
            AuthUI.getInstance().signOut(this)
                .addOnSuccessListener {
                    val intent = Intent(this, SigninActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Successfully Log Out", Toast.LENGTH_SHORT).show()
                }
        }


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
    interface OnMovieItemClickListener{
        fun onItemClicked(movie:Movies ,position:Int)
    }

}