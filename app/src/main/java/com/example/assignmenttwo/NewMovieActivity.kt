package com.example.assignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.assignmenttwo.databinding.ActivityMainBinding
import com.example.assignmenttwo.databinding.ActivityNewMovieBinding
import com.google.firebase.firestore.FirebaseFirestore

class NewMovieActivity : AppCompatActivity() {
    //setup view binding
    private lateinit var binding: ActivityNewMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userTextView.text="Hello "+intent.getStringExtra("userName")



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
}