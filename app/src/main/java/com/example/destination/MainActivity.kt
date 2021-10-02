package com.example.destination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView.background = null
        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.movieFragment -> findNavController(R.id.fragment).navigate(R.id.movieFragment)
                R.id.showFragment -> findNavController(R.id.fragment).navigate(R.id.showFragment2)
                R.id.FavoritesFragment -> findNavController(R.id.fragment).navigate(R.id.FavoritesFragment)
                R.id.SettingFragment -> findNavController(R.id.fragment).navigate(R.id.settingFragment)
            }
            true
        }
    }

}