package com.example.assignmenttwo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ReviewModelFactory(private  val movieID:String) :ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


            if(modelClass.isAssignableFrom(ReviewViewModel::class.java))
                return ReviewViewModel(movieID) as T

    else
        throw IllegalArgumentException("Invalid view model")


    }


}