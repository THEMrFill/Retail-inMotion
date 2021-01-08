package com.retailinmotion.philip.arnold.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.retailinmotion.philip.arnold.databinding.StopAdapterLineBinding
import com.retailinmotion.philip.arnold.model.Tram

class StopAdapter: RecyclerView.Adapter<StopHolder>() {
    private var stops: List<Tram> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopHolder {
        val binding = StopAdapterLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StopHolder(binding)
    }

    override fun onBindViewHolder(holder: StopHolder, position: Int) {
        holder.bind(stops[position])
    }

    override fun getItemCount() = stops.size

    fun setStops(newStops: List<Tram>) {
        stops = newStops
        notifyDataSetChanged()
    }
}

class StopHolder(val binding: StopAdapterLineBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(tram: Tram) {
        binding.tram = tram
    }
}
