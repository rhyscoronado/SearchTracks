package com.code.home

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.code.model.SongDetail
import com.rhyscoronado.codingchallenge.R
import com.rhyscoronado.codingchallenge.databinding.ItemTrackListBinding

typealias TrackClickListener = (SongDetail) -> Unit

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.TracksViewHolder>() {

    private lateinit var tracksList: List<SongDetail>
    private lateinit var trackClickListener: TrackClickListener
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        this.context = parent.context
        val binding: ItemTrackListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_track_list, parent, false
        )
        return TracksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tracksList.size
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        holder.onBind(tracksList[position])
        holder.itemView.setOnClickListener { trackClickListener(tracksList[position]) }
    }

    fun updateTracksList(tracksList: List<SongDetail>) {
        this.tracksList = tracksList
        notifyDataSetChanged()
    }
//
    fun setTrackClickListener(trackClickListener: TrackClickListener){
        this.trackClickListener = trackClickListener
    }

    inner class TracksViewHolder(private val binding: ItemTrackListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(obj: SongDetail) {

            Glide.with(context).load(obj.artworkUrl60).into(binding.ivArtwork);
            binding.tvGenre.text = obj.primaryGenreName
            binding.tvTrackName.text = obj.trackName
            binding.tvPrice.text = String.format("$%s", obj.trackPrice)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(adapterPosition % 2 == 0) {
                    binding.trackContainer.setBackgroundColor(context.getColor(R.color.colorPrimary))
                } else {
                    binding.trackContainer.setBackgroundColor(context.getColor(R.color.colorPrimaryDark))
                }

            }

        }
    }
}