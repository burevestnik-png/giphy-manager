package com.example.giphyManager.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyManager.list.R
import com.example.giphyManager.list.databinding.ListItemGifBinding
import com.example.giphyManager.list.domain.model.UiGif
import timber.log.Timber

fun interface GifClickListener {
    fun onGifClick()
}

class GifAdapter(
    private val gifs: MutableList<UiGif> = mutableListOf(),
    private val onClickListener: GifClickListener
) : RecyclerView.Adapter<GifAdapter.UiGifViewHolder>() {

    fun updateGifs(gifs: List<UiGif>) {
        this.gifs.clear()
        this.gifs.addAll(gifs)
        notifyDataSetChanged()
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
            Timber.d(uiGif.url)
            binding.apply {
                title.text = uiGif.id

                Glide
                    .with(binding.root.context)
                    .asGif()
                    .load("https://media3.giphy.com/media/ZPRG8yKKLXiuSlo2q1/200w.gif?cid=4738eb2160goxon53jfrjd239co00gm9fp1xutzjybtg560m&rid=200w.gif&ct=g")
                    .into(gif)
            }
        }
    }
}