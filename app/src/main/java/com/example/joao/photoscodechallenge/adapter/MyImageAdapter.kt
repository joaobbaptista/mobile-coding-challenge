package com.example.joao.photoscodechallenge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joao.photoscodechallenge.R
import com.example.joao.photoscodechallenge.entry.Photo
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.view.*

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyImageAdapter(private val photos: MutableList<Photo>, val listener: Listener)
    : RecyclerView.Adapter<MyImageAdapter.ViewHolder>() {

    companion object {
        const val TYPE_IMAGE = 0
        const val TYPE_FOOTER = 1
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.image_item, parent, false))
    }

    override fun getItemCount() = photos.size

    override fun getItemViewType(position: Int): Int {
        return if (isPositionFooter(position))
            TYPE_FOOTER
        else
            TYPE_IMAGE
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val photo = photos[position]

        Picasso
                .get()
                .load(photo.smallUrl)
                .into(holder.itemView.image)

        RxView.
                clicks(holder.itemView)
                .subscribe({
                    listener.onItemClickAtPosition(position)
                })
    }

    fun appendImages(newPhotos: List<Photo>) {
        photos.addAll(newPhotos)
        notifyItemRangeInserted(itemCount + 1, newPhotos.size)
    }
}

interface Listener {
    fun onItemClickAtPosition(position: Int)
}