package com.example.destination

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.destination.model.country.Data
import com.example.destination.network.API
import com.example.destination.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            val country:String = etEnterCountry.text.toString()
            getId(country)
            Log.d("MyLog","Mistake")
        }



    }
    fun getId(country:String){
        val retrofit = RetrofitClient.getClient(getString(R.string.base_url)).create(API::class.java)
        retrofit.searchID(country)
            .enqueue(object : Callback<Data> {
                override fun onResponse(
                    call: Call<Data>,
                    response: Response<Data>
                ) {
                    if (response.body() != null) {
                        tvIdCountry.text = response.body().toString()
                        Log.d("MyLog",response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    Toast.makeText(context,"Ошибка", Toast.LENGTH_LONG).show()
                    Log.d("MyLog","Mistake")
                }
            })

    }



}