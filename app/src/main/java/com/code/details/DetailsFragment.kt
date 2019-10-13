package com.code.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.code.home.MainActivity
import com.code.injection.ViewModelFactory
import com.code.model.SongDetail
import com.rhyscoronado.codingchallenge.BuildConfig
import com.rhyscoronado.codingchallenge.databinding.FragmentTrackDetailsBinding
import timber.log.Timber

class DetailsFragment : Fragment() {

    private lateinit var activity: MainActivity
    private lateinit var binding: FragmentTrackDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTrackDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val track = DetailsFragmentArgs.fromBundle(arguments!!).track
        loadDetails(track)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    fun loadDetails(track: SongDetail) {

        Glide.with(activity).load(track.artworkUrl100).into(binding.ivArtwork)
        binding.tvLongDescription.text = track.longDescription
    }

}