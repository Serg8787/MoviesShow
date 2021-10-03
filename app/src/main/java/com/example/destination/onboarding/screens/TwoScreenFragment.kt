package com.example.destination.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.destination.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlinx.android.synthetic.main.fragment_splash_screen.ivSplash
import kotlinx.android.synthetic.main.fragment_two_screen.*
import kotlinx.android.synthetic.main.fragment_two_screen.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [TwoScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoScreenFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_two_screen, container, false)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)


        view.btNextTwo.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return view
    }



}