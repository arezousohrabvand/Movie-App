package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.GC200387480.assignmenttwo.databinding.ActivityReviewBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore

class ReviewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReviewBinding
    private lateinit var viewModel: ReviewViewModel
    private lateinit var  viewModelFactory: ReviewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    binding.movieName.text=intent.getStringExtra("movieName")
        val movieID=intent.getStringExtra("movieID")
        binding.savingBtn.setOnClickListener{
            val inputName=binding.userName.text.toString()
            var likeInput=binding.likeText.text.toString()
            var favoriteInput=binding.favoriteScene.text.toString()
            var recommendInput=binding.recomendText.text.toString()
            if(inputName.isNotEmpty() &&
                likeInput.isNotEmpty() &&
                favoriteInput.isNotEmpty()&&
                recommendInput.isNotEmpty()){
                val db= FirebaseFirestore.getInstance().collection("reviews")
                val id=db.document().id
               movieID?.let {
                   val newReview =
                       Review(id, inputName, likeInput, favoriteInput, recommendInput, movieID)
                   db.document().set(newReview)
                       .addOnSuccessListener {
                           Toast.makeText(this, "Your movie information added", Toast.LENGTH_LONG)
                               .show()

                       }
                       .addOnFailureListener {
                           Toast.makeText(this, "Your database have problem !!", Toast.LENGTH_LONG)
                               .show()

                       }
               }


            }
            else{
                Toast.makeText(this,"You should fill all of the fields", Toast.LENGTH_LONG).show()
            }

            }
        movieID?.let{
            viewModelFactory= ReviewModelFactory(movieID)
            viewModel=ViewModelProvider(this,viewModelFactory).get(ReviewViewModel::class.java)
            viewModel.getReviews().observe(this,{reviews ->
                var listRecyclerview=ReviewAdapter(this,reviews )
                binding.reviewList.adapter=listRecyclerview
                
            })

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

                    val intent = Intent(this, AboutMeActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "You choose Profile page", Toast.LENGTH_SHORT).show()

        }
        if(id == R.id.homepage){

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "You choose Home page", Toast.LENGTH_SHORT).show()

        }
        return super.onOptionsItemSelected(item)
    }



    }
