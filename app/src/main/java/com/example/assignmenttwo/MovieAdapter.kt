package com.example.assignmenttwo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class MovieAdapter(
    val context: Context,
    val movies: List<Movies>,val clickListener:OnMovieItemClickListener
):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
        inner class  MovieViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView){
            val filmTextView=itemView.findViewById<TextView>(R.id.filmName)
            val genreTextView=itemView.findViewById<TextView>(R.id.genreMovie)
            val summartTextView=itemView.findViewById<TextView>(R.id.summmaryMovie)
            val directorTextView=itemView.findViewById<TextView>(R.id.directorMovie)
            val rating=itemView.findViewById<RatingBar>(R.id.ratingBar)


        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_movie_grid,parent,false)
        return  MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie=movies[position]

            holder.filmTextView.text=movie.movieName
            holder.genreTextView.text=movie.genreMovie
            holder.summartTextView.text=movie.summaryMovie
            holder.directorTextView.text=movie.directorMovie
            holder.rating.rating=movie.imdbrating!!.toFloat()
        holder.itemView.setOnClickListener{
            clickListener.onItemClicked(movie)
        }


    }

    override fun getItemCount(): Int {
        return  movies.size
    }
    interface OnMovieItemClickListener{
        fun onItemClicked(movie:Movies)
    }



    }


