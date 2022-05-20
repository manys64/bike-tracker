package com.example.bikeTracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coctails.R


class CaptionedImagesAdapter(private var captions: MutableList<String>, private var imageIds: MutableList<Int>, private var listener: Listener)
    : RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {

    interface Listener {
        fun onClick(position: Int)
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun getItemCount(): Int {
        return captions.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_captioned_image, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView: CardView = holder.cardView
        val imageView: ImageView = cardView.findViewById<View>(R.id.info_image) as ImageView
        val drawable = ContextCompat.getDrawable(cardView.context, imageIds[position])
        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]
        val textView = cardView.findViewById<View>(R.id.info_text) as TextView
        textView.text = captions[position]

        cardView.setOnClickListener { listener.onClick(position) }
    }
}