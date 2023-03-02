package com.example.giphyManager.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.giphyManager.list.R
import com.example.giphyManager.list.databinding.ListItemGifBinding
import com.example.giphyManager.list.domain.model.UiGif
import timber.log.Timber

fun interface GifClickListener {
    fun onGifClick()
}

class GifAdapter(
    private var gifs: List<UiGif> = emptyList(),
    private val onClickListener: GifClickListener
) : RecyclerView.Adapter<GifAdapter.UiGifViewHolder>() {

    fun updateGifs(gifs: List<UiGif>) {
        if (gifs.size != this.gifs.size || gifs.firstOrNull()?.equals(this.gifs.firstOrNull()) == false) {
            this.gifs = gifs
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UiGifViewHolder =
        UiGifViewHolder(
            ListItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: UiGifViewHolder, position: Int) =
        holder.bind(gifs[position])

    override fun getItemCount(): Int = gifs.size

    inner class UiGifViewHolder(private val binding: ListItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uiGif: UiGif) {
            binding.apply {
                title.text = uiGif.title

                Glide
                    .with(binding.root.context)
                    .asGif()
                    .load(uiGif.url)
                    .into(gif)
            }
        }
    }
}