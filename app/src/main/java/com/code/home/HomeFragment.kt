package com.code.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.code.injection.ViewModelFactory
import com.code.model.SongDetail
import com.rhyscoronado.codingchallenge.BuildConfig
import com.rhyscoronado.codingchallenge.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment() {

    private val clickListener: TrackClickListener = this::onTrackClick
    private lateinit var activity: MainActivity
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    private val tracksAdapter : TracksAdapter = TracksAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        homeViewModel = ViewModelProviders.of(this, ViewModelFactory(activity)).get(HomeViewModel::class.java)
        tracksAdapter.setTrackClickListener(clickListener)

        /**
         * This code observe the error message if it is not null and if it contain any value at all
         * if it does we just show toast message
         */

        homeViewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            Timber.e(message)
        })

        homeViewModel.songDetailList.observe(this, Observer { songDetailList ->

            Timber.e(songDetailList.toString())
            tracksAdapter.updateTracksList(songDetailList)
            binding.rvTracks.adapter = tracksAdapter

            tracksAdapter.notifyDataSetChanged()
        })


        /**
         * This observer methods observe the value changes from ViewModel and propagates the value changes here
         * and set the value accordingly to the UI
         */
        homeViewModel.loadingVisibility.observe(this, Observer { visibility ->
            pbFragmentHome.visibility = visibility
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as MainActivity
    }


    private fun onTrackClick(track: SongDetail) {
        if (track.longDescription != null) {
            val navDirections = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(track)
            view?.let { findNavController().navigate(navDirections) }
        } else {
            Toast.makeText(context, "No Description Available", Toast.LENGTH_LONG).show()
        }

    }
}