package com.zennymorh.lawconferenceapp.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zennymorh.lawconferenceapp.R
import com.zennymorh.lawconferenceapp.adapter.CategoryAdapter
import com.zennymorh.lawconferenceapp.adapter.EventAdapter
import com.zennymorh.lawconferenceapp.adapter.EventItemClickListener
import com.zennymorh.lawconferenceapp.adapter.FavouriteClickListener
import com.zennymorh.lawconferenceapp.db.EventDatabase
import com.zennymorh.lawconferenceapp.models.Category
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    private var categoryArray = arrayListOf(
        Category(
            "Litigation",
            R.drawable.litigation,
            R.color.litigation
        ),
        Category(
            "Banking",
            R.drawable.banking,
            R.color.banking
        ),
        Category(
            "Corporate",
            R.drawable.corporate,
            R.color.corporate
        ),
        Category(
            "Family",
            R.drawable.family,
            R.color.family
        )
    )

    private lateinit var viewModel: HomeViewModel

    private val eventAdapter: EventAdapter by lazy{
        EventAdapter(arrayListOf(), onEventItemSelected, favouriteClicked)
    }

    private val onEventItemSelected by lazy {
        object : EventItemClickListener {
            override fun invoke(event: Event) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(event)
                findNavController().navigate(action)
            }
        }
    }

    private val favouriteClicked by lazy {
        object : FavouriteClickListener {
            override fun invoke(event: Event) {
                viewModel.updateEventFavourite(event)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        areaRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        areaRecyclerView.adapter =
            CategoryAdapter(
                categoryArray,
                requireContext()
            )

        eventRecyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        eventRecyclerView.adapter = eventAdapter

        viewModel.events.observe(viewLifecycleOwner, Observer { newList ->
            eventAdapter.updateList(newList)
        })

        favouriteButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
        }

    }

}
