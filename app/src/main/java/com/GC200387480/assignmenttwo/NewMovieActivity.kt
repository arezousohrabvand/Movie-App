package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.GC200387480.assignmenttwo.databinding.ActivityNewMovieBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore

class NewMovieActivity : AppCompatActivity() {
    //setup view binding
    private lateinit var binding: ActivityNewMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.saveBtn.setOnClickListener{
            if(binding.addMovieEditText.text.toString().isNotEmpty() &&
                binding.addSummary.text.toString().isNotEmpty() &&
                    binding.addWriter.text.toString().isNotEmpty()&&
                    binding.addGenre.text.toString().isNotEmpty()&&
                    binding.addDirector.text.toString().isNotEmpty()&&
                    binding.addStars.text.toString().isNotEmpty()&& binding.spinner.selectedItemPosition>0
                    ){
                val movies=Movies()
                movies.movieName=binding.addMovieEditText.text.toString()
                movies.directorMovie=binding.addDirector.text.toString()
                movies.genreMovie=binding.addGenre.text.toString()
                movies.summaryMovie=binding.addSummary.text.toString()
                movies.writerMovie=binding.addWriter.text.toString()
                movies.starMovie=binding.addStars.text.toString()
                movies.imdbrating=binding.spinner.selectedItem.toString().toInt()





                val db=FirebaseFirestore.getInstance().collection("movies")
                movies.id=db.document().id
                db.document(movies.id!!).set(movies)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Your movie information added",Toast.LENGTH_LONG).show()
                        binding.addMovieEditText.setText("")
                        binding.addDirector.setText("")
                        binding.addGenre.setText("")
                        binding.addStars.setText("")
                        binding.addWriter.setText("")
                        binding.addSummary.setText("")
                        binding.spinner.setSelection(0)

                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Your database have problem !!",Toast.LENGTH_LONG).show()

                    }


            }
            else{
                Toast.makeText(this,"You should fill all of the fields",Toast.LENGTH_LONG).show()
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
}