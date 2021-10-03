package com.example.destination.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.destination.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlinx.android.synthetic.main.fragment_splash_screen.ivSplash
import kotlinx.android.synthetic.main.fragment_three_screen.*
import kotlinx.android.synthetic.main.fragment_three_screen.view.*
import kotlinx.android.synthetic.main.fragment_two_screen.*


/**
 * A simple [Fragment] subclass.
 * Use the [ThreeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThreeScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val view = inflater.inflate(R.layout.fragment_three_screen, container, false)

        view.btNextThree.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment2)
            onBoardingFinished()
        }

        return view
    }



    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}