package com.zennymorh.lawconferenceapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.zennymorh.lawconferenceapp.R
import com.zennymorh.lawconferenceapp.models.Event
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val event = args.selectedEvent

        bindTexts(event)
        displayImages(event)
        displayDate(event)

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun bindTexts(event: Event) {
        eventTitleTV.text =
            "${event.title} '${event.year.substring(event.year.length - 2, event.year.length)}"
        locationTV.text = event.address
        overviewTV.text = event.overview
        rating.text = event.star.toString()
        attendeeNumberTV.text = "+ ${event.attendees.size - 3} others attending"
    }

    private fun displayImages(event: Event) {
        Picasso.get().load(event.picture).into(eventImageView)
        Picasso.get().load(event.attendees[0].picture).into(attendee_one_img)
        Picasso.get().load(event.attendees[1].picture).into(attendee_two_img)
        Picasso.get().load(event.attendees[2].picture).into(attendee_three_img)
    }

    private fun displayDate(event: Event) {
        val formatter = SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss 'Z'", Locale.getDefault())
        val startDate = formatter.parse(event.startDate)
        val startDateFormatted = SimpleDateFormat("dd MMM", Locale.getDefault()).format(startDate)

        val endDate = formatter.parse(event.endDate)
        val endDateFormatted = SimpleDateFormat("dd MMM", Locale.getDefault()).format(endDate)

        dateTV.text = "$startDateFormatted - $endDateFormatted"
    }

}
