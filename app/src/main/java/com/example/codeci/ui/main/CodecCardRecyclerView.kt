package com.example.codeci.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codeci.R

class CodecCardRecyclerView(inputData: List<MediaCodecInfoPair>, val onClick: (String) -> Unit) :
    RecyclerView.Adapter<CodecCardRecyclerView.ViewHolder>() {

    private val data: List<Pair<String, String>>

    init {
        data = inputData.map {
            it.first to it.second.supportedTypes[0] +
                    if (it.second.supportedTypes.size > 1)
                        " (+${it.second.supportedTypes.size-1})"
                    else ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.codec_name_card_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.codecName.text = data[position].first
        holder.codecType.text = data[position].second
        holder.itemView.setOnClickListener {
            onClick(data[position].first)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val codecName: TextView = view.findViewById(R.id.codec_name_value)
        val codecType: TextView = view.findViewById(R.id.codec_type_value)
    }
}