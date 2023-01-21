package com.shivam.musicwiki.recyclerview_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.musicwiki.BR
import com.shivam.musicwiki.databinding.ArtistsItemBinding
import com.shivam.musicwiki.databinding.TrackItemBinding
import com.shivam.musicwiki.interfaces.IMainActivity
import com.shivam.musicwiki.models.artists.ArtistsDataClass
import com.shivam.musicwiki.models.tracks.TracksDataClass
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TracksAdapter(private val context: Context, private var tracksList: MutableList<TracksDataClass>) :
    RecyclerView.Adapter<TracksAdapter.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {

        val rooView: ViewDataBinding =
            TrackItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(rooView)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {

        val trackCurrent = tracksList[position]
        holder.itemBinding.setVariable(BR.trackItem,trackCurrent)
        holder.itemBinding.setVariable(BR.listener, context as IMainActivity)
        holder.itemBinding.executePendingBindings()

        var thumbnailImage: String? = null
        CoroutineScope(Dispatchers.IO).launch {

            thumbnailImage = tracksList[position].image[2].text.toString()
            withContext(Dispatchers.Main) {
                if (thumbnailImage != null) {
                    Glide.with(context)
                        .load(thumbnailImage)
                        .into(holder.itemBinding.root.roundedImageView)
                }
            }
        }
    }

    override fun getItemCount(): Int {

        return tracksList.size
    }

    fun updateData(newDataList: List<TracksDataClass>) {
        val oldList = tracksList
        val diffUtil: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            MovieItemDiffCallback(
                oldList, newDataList
            )
        )
        tracksList = newDataList.toMutableList()
        diffUtil.dispatchUpdatesTo(this)
    }

    class MovieItemDiffCallback(
        var oldGenreList: List<TracksDataClass>,
        var newGenreList: List<TracksDataClass>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldGenreList.size
        }

        override fun getNewListSize(): Int {
            return newGenreList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldGenreList[oldItemPosition].name == newGenreList[newItemPosition].name!!)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldGenreList[oldItemPosition] == newGenreList[newItemPosition])
        }
    }

    class BindingViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

}