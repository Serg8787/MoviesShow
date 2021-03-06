package com.example.destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.adapter.OnClickMovieItem
import com.example.destination.adapter.OnClickShowItem
import com.example.destination.adapter.ShowAdapter
import com.example.destination.model.movie.MovieResult
import com.example.destination.models.show.ShowResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_show.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowFragment : Fragment() {
    private lateinit var viewModel: ShowViewModel
    private lateinit var adapterPopularityShows: ShowAdapter
    private lateinit var adapterTopRatedShows: ShowAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.bottomNavView.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(ShowViewModel::class.java)


        setupRecyclerView()
        switchShows.isChecked = false

        tvPopularityShow.setTextColor(resources.getColor(R.color.teal_200))


        getTopRatedShowsData()
        switchShows.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                getPopularityShowsData()
                rvTopRatedShow.visibility = View.GONE
                rvPopularityShow.visibility = View.VISIBLE
                tvPopularityShow.setTextColor(resources.getColor(android.R.color.holo_orange_light))
                tvTopRatedShow.setTextColor(resources.getColor(R.color.teal_200))
            } else {
                getTopRatedShowsData()
                rvTopRatedShow.visibility = View.VISIBLE
                rvPopularityShow.visibility = View.GONE
                tvPopularityShow.setTextColor(resources.getColor(R.color.teal_200))
                tvTopRatedShow.setTextColor(resources.getColor(android.R.color.holo_orange_light))

            }
        }

        adapterTopRatedShows.onClickShowItem = object : OnClickShowItem {
            override fun itemShowSelected(showResult: ShowResult) {
                val args = Bundle().apply {
                    putParcelable("show",showResult)
                }
                findNavController().navigate(R.id.action_showFragment2_to_showDetailFragment,args)
            }

        }
        adapterPopularityShows.onClickShowItem = object : OnClickShowItem {
            override fun itemShowSelected(showResult: ShowResult) {
                val args = Bundle().apply {
                    putParcelable("show",showResult)
                }
                findNavController().navigate(R.id.action_showFragment2_to_showDetailFragment,args)
            }

        }
    }

    fun setupRecyclerView() {
        adapterPopularityShows = ShowAdapter()
        rvPopularityShow.adapter = adapterPopularityShows


        adapterTopRatedShows = ShowAdapter()
        rvTopRatedShow.adapter = adapterTopRatedShows

    }

    fun getPopularityShowsData() {
        viewModel.loadPopulatityShow(page)
        viewModel.listShowPopularity.observe(viewLifecycleOwner, Observer {
            adapterPopularityShows.updateList(it as ArrayList<ShowResult>)
        })
        rvPopularityShow.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =
                    (rvPopularityShow.layoutManager as LinearLayoutManager).itemCount
                val visibleItemCount: Int =
                    (rvPopularityShow.layoutManager as LinearLayoutManager).childCount
                val firstVisibleItem: Int =
                    (rvPopularityShow.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    rvPopularityShow.removeOnScrollListener(this)
                    page++
                    getPopularityShowsData()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    fun getTopRatedShowsData() {
        viewModel.loadTopRatedShow(page)
        viewModel.listShowTopRated.observe(viewLifecycleOwner, Observer {
            adapterTopRatedShows.updateList(it as ArrayList<ShowResult>)
        })
        rvTopRatedShow.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =
                    (rvTopRatedShow.layoutManager as LinearLayoutManager).itemCount
                val visibleItemCount: Int =
                    (rvTopRatedShow.layoutManager as LinearLayoutManager).childCount
                val firstVisibleItem: Int =
                    (rvTopRatedShow.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    rvPopularityShow.removeOnScrollListener(this)
                    page++
                    getTopRatedShowsData()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }
}