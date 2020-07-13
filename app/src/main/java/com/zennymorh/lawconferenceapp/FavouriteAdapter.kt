package com.zennymorh.lawconferenceapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.favourite_item.view.*
import java.util.*

typealias EventItemClickListener = (Event) -> Unit
typealias FavouriteClickListener = (Event) -> Unit

class FavouriteAdapter(private var eventList:List<Event>, var eventItemClickListener: EventItemClickListener, var favouriteListener: FavouriteClickListener): RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavouriteViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val event: Event = eventList[position]
        holder.bind(event)
    }

    fun updateList(list: List<Event>) {
        eventList = list
        notifyDataSetChanged()
    }

    inner class FavouriteViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(
            R.layout.favourite_item, parent,
            false)), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(event: Event) {
            itemView.eventNameTV.text = "${event.title} ${event.year}"
            itemView.eventLocationTV.text = "${event.city}, ${event.country}"
            itemView.favouriteRed.setOnClickListener {
                favouriteListener.invoke(event)
            }
        }

        override fun onClick(p0: View?) {
            val event = eventList[adapterPosition]
            eventItemClickListener.invoke(event)
        }
    }
}