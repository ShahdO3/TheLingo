package com.example.thelingo_projectshahdosman

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var move:NavDirections

        Handler(Looper.myLooper()!!).postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser

            move = if (currentUser == null){
                SplashScreenDirections.actionSplashScreenToSignUpFragment()
            }else{
                val sharedPref = requireActivity().getSharedPreferences("savePic",
                    Context.MODE_PRIVATE)
                val imgUri = sharedPref.getString("imgUri", "")!!
                println("sent image = $imgUri")
                SplashScreenDirections.actionSplashScreenToProfilePageFragment(imgUri)
            }
            findNavController().navigate(move)
        }, 1000)

        return inflater.inflate(R.layout.activity_splash_screen, container, false)
    }
}