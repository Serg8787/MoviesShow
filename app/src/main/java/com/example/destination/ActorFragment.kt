package com.example.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.destination.adapter.ActorAdapter
import com.example.destination.adapter.OnClickActorItem
import com.example.destination.model.actors.ActorResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_actor.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActorFragment : Fragment() {
    private lateinit var viewModel:ActorViewModel
    private lateinit var adapterPopularityActor: ActorAdapter
    private var page = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actor, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.bottomNavView.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(ActorViewModel::class.java)
        setupRecyclerView()


        getPopularityActorData()


        adapterPopularityActor.onClickActorItem = object : OnClickActorItem{
            override fun itemActorSelected(actorResult: ActorResult) {
                val args = Bundle().apply {
                    putParcelable("actor",actorResult)
                }
                findNavController().navigate(R.id.action_actorFragment_to_actorDeteilFragment,args)
            }
        }
    }
    fun setupRecyclerView(){
        adapterPopularityActor = ActorAdapter()
        rvPopularityActors.adapter = adapterPopularityActor

    }
    fun getPopularityActorData() {
        viewModel.loadPopulatyActor(page)
        viewModel.listActorPopularity.observe(viewLifecycleOwner, Observer {
            adapterPopularityActor.updateList(it as ArrayList<ActorResult>)
        })
        rvPopularityActors.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount =
                    (rvPopularityActors.layoutManager as LinearLayoutManager).itemCount
                val visibleItemCount: Int =
                    (rvPopularityActors.layoutManager as LinearLayoutManager).childCount
                val firstVisibleItem: Int =
                    (rvPopularityActors.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    rvPopularityActors.removeOnScrollListener(this)
                    page++
                    getPopularityActorData()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}