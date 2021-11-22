package com.example.assignmenttwo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore


class MovieListViewModel :ViewModel(){
    private val movies = MutableLiveData<List<Movies>>()
    init {
        showMovies()
    }

    private fun showMovies() {
        val db= FirebaseFirestore.getInstance().collection("movies")


        db.addSnapshotListener{ documents, exception ->


            exception?.let {
                Log.i("Database_Response", "failed : "+ exception)
                return@addSnapshotListener
            }


            val moviesList = ArrayList<Movies>()


            documents?.let{
                for (document in documents)
                {
                    try {
                        val movie = document.toObject(Movies::class.java)
                        moviesList.add(movie)
                    }catch(e : Exception)
                    {
                        Log.i("DB_Response", document.toString())
                    }

                }
            }
            movies.value = moviesList
        }
    }
    fun getMovies() : LiveData<List<Movies>>
    {
        return  movies
    }

}