package com.example.destination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavView.background = null
        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> findNavController(R.id.fragment).navigate(R.id.homeFragment2)
                R.id.showFragment -> findNavController(R.id.fragment).navigate(R.id.showFragment2)
                R.id.serialFragment -> findNavController(R.id.fragment).navigate(R.id.serialFragment2)
            }
            true
        }


    }


}