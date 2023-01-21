package com.shivam.musicwiki.recyclerview_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shivam.musicwiki.databinding.ItemGenreBinding
import com.shivam.musicwiki.interfaces.IMainActivity
import com.shivam.musicwiki.models.genre.Tag
import com.shivam.musicwiki.BR

class GenreAdapter(private val context: Context, private var genreList: MutableList<Tag>) :
    RecyclerView.Adapter<GenreAdapter.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {

        val rooView: ViewDataBinding =
            ItemGenreBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(rooView)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {

        val genreCurrent = genreList[position]
        holder.itemBinding.setVariable(BR.genreItem, genreCurrent)
        holder.itemBinding.setVariable(BR.listener, context as IMainActivity)
        holder.itemBinding.executePendingBindings()

//        holder.itemBinding.root.


    }

    override fun getItemCount(): Int {

        return genreList.size
    }

    fun updateData(newDataList: List<Tag>) {
        val oldList = genreList
        val diffUtil: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            MovieItemDiffCallback(
                oldList, newDataList
            )
        )
        genreList = newDataList.toMutableList()
        diffUtil.dispatchUpdatesTo(this)
    }

    class MovieItemDiffCallback(
        var oldGenreList: List<Tag>,
        var newGenreList: List<Tag>
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