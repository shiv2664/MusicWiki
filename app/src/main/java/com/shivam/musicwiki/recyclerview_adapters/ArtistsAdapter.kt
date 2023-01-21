package com.shivam.musicwiki.recyclerview_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.musicwiki.BR
import com.shivam.musicwiki.databinding.AlbumItemBinding
import com.shivam.musicwiki.databinding.ArtistsItemBinding
import com.shivam.musicwiki.interfaces.IMainActivity
import com.shivam.musicwiki.models.artists.ArtistsDataClass
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistsAdapter(private val context: Context, private var artistList: MutableList<ArtistsDataClass>) :
    RecyclerView.Adapter<ArtistsAdapter.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {

        val rooView: ViewDataBinding =
            ArtistsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(rooView)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {

        val artistCurrent = artistList[position]
        holder.itemBinding.setVariable(BR.artistItem, artistCurrent)
        holder.itemBinding.setVariable(BR.listener, context as IMainActivity)
        holder.itemBinding.executePendingBindings()

        var thumbnailImage: String? = null
        CoroutineScope(Dispatchers.IO).launch {

            thumbnailImage = artistList[position].image[0].text.toString()
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

        return artistList.size
    }

    fun updateData(newDataList: List<ArtistsDataClass>) {
        val oldList = artistList
        val diffUtil: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            MovieItemDiffCallback(
                oldList, newDataList
            )
        )
        artistList = newDataList.toMutableList()
        diffUtil.dispatchUpdatesTo(this)
    }

    class MovieItemDiffCallback(
        var oldGenreList: List<ArtistsDataClass>,
        var newGenreList: List<ArtistsDataClass>
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