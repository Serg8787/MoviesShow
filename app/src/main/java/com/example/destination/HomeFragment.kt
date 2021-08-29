package com.example.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.destination.model.Show
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient

import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btSend.setOnClickListener {
            getData()
            Log.d("MyLog","кнопка нажата")
        }
    }
    fun getData(){
        val retrofit = RetrofitClient.getClient("https://api.tvmaze.com/search/").create(API::class.java)
        retrofit.search("girls")
            .enqueue(object : retrofit2.Callback<Show> {
                override fun onResponse(
                    call: Call<Show>,
                    response: Response<Show>
                ) {
                    if (response.body() != null) {
                        Log.d("MyLog","good" + response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Show>, t: Throwable) {
                    Log.d("MyLog","good" + t.toString())
                }
            })
    }
}