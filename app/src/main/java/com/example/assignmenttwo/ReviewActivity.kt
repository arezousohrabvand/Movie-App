package com.example.assignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.assignmenttwo.databinding.ActivityReviewBinding
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



    }
