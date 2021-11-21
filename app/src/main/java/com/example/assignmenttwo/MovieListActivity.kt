package com.example.assignmenttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.assignmenttwo.databinding.ActivityMovieListBinding
import com.example.assignmenttwo.databinding.ActivityTopMovieBinding
import com.google.firebase.firestore.FirebaseFirestore

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMovieListBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db=FirebaseFirestore.getInstance().collection("movies")
         val query=db.get().addOnSuccessListener {
             documents->
             for (document in documents){
                 Log.i("Database_Response","${document.data}")

                 val movie=document.toObject(Movies::class.java)

                 val txtView=TextView(this)
                 txtView.text=movie.movieName
                 txtView.textSize=25f
                 val txtView2=TextView(this)
                 txtView2.text=movie.genreMovie
                 txtView2.textSize=15f
                 binding.linearLayout.addView(txtView)
                 binding.linearLayout.addView(txtView2)

             }
         }

    }
}