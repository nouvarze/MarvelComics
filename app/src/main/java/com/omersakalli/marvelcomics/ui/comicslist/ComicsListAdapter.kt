package com.omersakalli.marvelcomics.ui.comicslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.omersakalli.marvelcomics.R
import com.omersakalli.marvelcomics.data.model.Thumbnail
import com.omersakalli.marvelcomics.databinding.ComicsListItemBinding
import com.omersakalli.marvelcomics.ui.model.Comic
import com.omersakalli.marvelcomics.utils.ImageUtils
import com.omersakalli.marvelcomics.utils.ImageUtils.extractUrl

class ComicsListAdapter(
    val onComicClickListener: OnComicClickListener
): RecyclerView.Adapter<ComicsListAdapter.ComicsListItem>() {
    var comicsList: ArrayList<Comic> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListItem {
        val binding: ComicsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.comics_list_item,
            parent,
            false
        )
        return ComicsListItem(binding)
    }

    override fun onBindViewHolder(holder: ComicsListItem, position: Int) {
        holder.bind(comicsList[position])
    }

    override fun getItemCount(): Int = comicsList.size


    inner class ComicsListItem(private val binding:ComicsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic){
            val context = binding.root.context
            binding.root.setOnClickListener { onComicClickListener.onComicClick(comicsList[adapterPosition]) }
            loadImage(comic.thumbnail, context)
        }

        private fun loadImage(thumbnail: Thumbnail?, context: Context){ //TODO: Save images
            thumbnail?.let { _->
                Glide.with(binding.root)
                    .load(thumbnail.extractUrl(ImageUtils.AspectRatio.PORTRAIT, context.resources.displayMetrics.densityDpi))
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivComicCover)
            } ?: kotlin.run {
                Glide.with(binding.root)
                    .load(R.drawable.image_not_available)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(binding.ivComicCover)
            }
        }
    }

}