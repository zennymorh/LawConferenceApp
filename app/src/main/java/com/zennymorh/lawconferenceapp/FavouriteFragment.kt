package com.zennymorh.lawconferenceapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment : Fragment() {

    private lateinit var viewModel: FavouriteViewModel

    private val favouriteAdapter: FavouriteAdapter by lazy{
        FavouriteAdapter(arrayListOf(),onEventItemSelected, favouriteClicked)
    }

    private val favouriteClicked by lazy {
        object : FavouriteClickListener {
            override fun invoke(event: Event) {
                viewModel.updateEventFavourite(event)
            }
        }
    }

    private val onEventItemSelected by lazy {
        object : EventItemClickListener {
            override fun invoke(event: Event) {
                val action = FavouriteFragmentDirections.actionFavouriteFragmentToDetailFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavouriteViewModel::class.java)


        favouriteRecyclerView.adapter = favouriteAdapter

        viewModel.events.observe(viewLifecycleOwner, Observer { newList ->
            favouriteAdapter.updateList(newList)
        })

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
