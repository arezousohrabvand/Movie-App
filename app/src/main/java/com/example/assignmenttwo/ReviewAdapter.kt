package com.example.assignmenttwo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter (val context: Context,
val reviews: List<Review>
): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){
    inner  class ReviewViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val userInputName = itemView.findViewById<TextView>(R.id.name_txtview)
        val likeInput=itemView.findViewById<TextView>(R.id.like_textview)
        val favoriteInput=itemView.findViewById<TextView>(R.id.favoratie_textview)
        val recommendInput=itemView.findViewById<TextView>(R.id.recommend_textview)
    }

    override fun onCreateViewHolder(reviewItem: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater=LayoutInflater.from(reviewItem.context)
        val view=inflater.inflate(R.layout.review_items,reviewItem,false)
        return  ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review=reviews[position]

        holder.userInputName.text=review.inputName
        holder.likeInput.text=review.like
        holder.favoriteInput.text=review.favorite
        holder.recommendInput.text=review.recommend

    }

    override fun getItemCount(): Int {
       return  reviews.size
    }
}