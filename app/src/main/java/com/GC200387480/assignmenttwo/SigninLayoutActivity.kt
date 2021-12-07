package com.GC200387480.assignmenttwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.GC200387480.assignmenttwo.databinding.ActivitySigninLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class SigninLayoutActivity : AppCompatActivity() {
    private  lateinit var  binding :ActivitySigninLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySigninLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.signinBtn.setOnClickListener{
            startActivity(Intent(this,CustomSiginActivity::class.java))
        }
    }




}