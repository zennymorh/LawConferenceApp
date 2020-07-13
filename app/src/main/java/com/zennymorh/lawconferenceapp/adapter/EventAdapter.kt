package com.zennymorh.lawconferenceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zennymorh.lawconferenceapp.App
import com.zennymorh.lawconferenceapp.R
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.android.synthetic.main.event_item.view.*
import java.util.*

typealias EventItemClickListener = (Event) -> Unit
typealias FavouriteClickListener = (Event) -> Unit


class EventAdapter(private var eventList:List<Event>, var eventItemClickListener: EventItemClickListener, var favouriteListener: FavouriteClickListener): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event: Event = eventList[position]
        holder.bind(event)
    }

    fun updateList(list: List<Event>) {
        eventList = list
        notifyDataSetChanged()
    }

    inner class EventViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(
            R.layout.event_item, parent,
            false)), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val event = eventList[adapterPosition]
            eventItemClickListener.invoke(event)
        }

        fun bind(event: Event) {
            itemView.eventNameTextView.text = "${event.title} '${event.year.substring(event.year.length - 2, event.year.length)}"
            itemView.eventLocationTextView.text = "${event.city}, ${event.country}"
            itemView.rating.text = event.star.toString()

            if (event.isFavourite) {
                itemView.favourite.setImageResource(R.drawable.ic_like_red)
            } else{
                itemView.favourite.setImageResource(R.drawable.ic_like)
            }

            itemView.favourite.setOnClickListener {
                favouriteListener.invoke(event)
            }

//            itemView.eventImageView.layoutParams.height = getRandomIntInRange(450, 250)

//            Picasso.get().load("https://waset.org/i/1140x400/static/images/cities/ottawa.jpg").into(itemView.eventImageView)
        }

        private fun getRandomIntInRange(max: Int, min: Int): Int {
            val mRandom = Random()
            return mRandom.nextInt(max - min + min) + min
        }
    }
}