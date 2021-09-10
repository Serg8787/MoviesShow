package com.example.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.destination.model.actors.ActorResult
import kotlinx.android.synthetic.main.fragment_actor_deteil.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [ActorDeteilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActorDeteilFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actor_deteil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actor: ActorResult = requireArguments().get("actor") as ActorResult
        tvNameActorDetail.text = actor.name
        tvOriginalTitleActorDetail.text = actor.known_for_department
//        tvRatingMovieDetail.text = movie.vote_average.toString()
//        tvReleaseDateMovieDetail.text = movie.release_date
//        tvOverviewMovieDetail.text = movie.overview

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280/" + actor.profile_path)
            .placeholder(R.drawable.icons8_placeholder).circleCrop()
            .into(imageViewBigPosterActor)
    }


}