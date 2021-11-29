package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.GC200387480.assignmenttwo.databinding.ActivityProfileBinding
import com.firebase.ui.auth.AuthUI

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            Toast.makeText(this,"You choose add page" , Toast.LENGTH_LONG).show()
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
            Toast.makeText(this,"You choose Movie list" , Toast.LENGTH_LONG).show()
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
}