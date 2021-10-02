package com.example.destination

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.example.destination.R.style.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val theme = prefs.getString("themes","")
        if(theme=="Лайт"){
            setTheme(Theme_MaterialComponents_Light)
        } else if (theme=="Темная") {
            setTheme(Theme_MaterialComponents_Bridge)
        }

        setContentView(R.layout.activity_main)

        supportActionBar?.hide()



        bottomNavView.background = null
        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.movieFragment -> findNavController(R.id.fragment).navigate(R.id.movieFragment)
                R.id.showFragment -> findNavController(R.id.fragment).navigate(R.id.showFragment2)
                R.id.favoritesFragment -> findNavController(R.id.fragment).navigate(R.id.favoritesFragment)
                R.id.settingFragment -> findNavController(R.id.fragment).navigate(R.id.settingsFragment)
            }
            true
        }
    }

}