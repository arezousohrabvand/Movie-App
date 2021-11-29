package com.GC200387480.assignmenttwo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ReviewViewModel(movieID:String):ViewModel() {
    private  val reviews= MutableLiveData<List<Review>>()
    init {
        val database=FirebaseFirestore.getInstance().collection("reviews")
            .whereEqualTo("movieID",movieID)
            .addSnapshotListener{documents,exception ->
                if(exception !=null)
                {
                    return@addSnapshotListener
                }
                documents?.let{
                    var reviewList=ArrayList<Review>()
                    for (document in documents){
                        val review=document.toObject(Review::class.java)
                        reviewList.add(review)
                    }
                    reviews.value=reviewList
                }

            }

    }
    fun getReviews() : LiveData<List<Review>>
    {
        return  reviews
    }


}
