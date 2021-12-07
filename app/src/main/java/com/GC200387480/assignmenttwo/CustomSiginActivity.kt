package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.GC200387480.assignmenttwo.databinding.ActivityCustomSiginBinding
import com.GC200387480.assignmenttwo.databinding.ActivitySigninLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//use https://firebase.google.com/docs/auth/android/google-signin this for authenticating firebase for login page

class CustomSiginActivity : AppCompatActivity() {
    private  lateinit var  binding :ActivityCustomSiginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCustomSiginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth


        binding.signbtn.setOnClickListener {
            var email=binding.emailInputEdit.text.toString()
            if(email.isNotEmpty()){
                //checkUserExists()
            }
        }



    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
         startActivity(Intent(this,MainActivity::class.java))

        }
    }
    private fun checkUserExists(email:String) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener {
                    task-> task.result?.signInMethods?.isEmpty().let {
                        newUser->
                if(newUser!!){

                }
            } }

    }




}