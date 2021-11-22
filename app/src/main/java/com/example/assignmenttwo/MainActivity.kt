package com.example.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.assignmenttwo.databinding.ActivityMainBinding
import com.example.assignmenttwo.databinding.ActivityNewMovieBinding

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


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id == R.id.newRelease){
            val startNewMovie = Intent(this, NewMovieActivity::class.java)
            startActivity(startNewMovie )
            Toast.makeText(this,"You choose New release Movie" ,Toast.LENGTH_LONG).show()
          return true
        }
        if(id == R.id.top){
            val startTopMovie = Intent(this, TopMovieActivity::class.java)
            startActivity(startTopMovie)
            Toast.makeText(this,"You choose Top Movie" ,Toast.LENGTH_LONG).show()
            return true
        }
        if(id == R.id.list){
            val startMovieList = Intent(this, MovieRecyclerActivity::class.java)
            startActivity(startMovieList)
            Toast.makeText(this,"You choose Movie list" ,Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}