package com.shivam.musicwiki.databindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import com.shivam.musicwiki.models.albums.AlbumDataClass
import com.shivam.musicwiki.models.artists.ArtistsDataClass
import com.shivam.musicwiki.models.genre.Tag
import com.shivam.musicwiki.models.tracks.TracksDataClass
import com.shivam.musicwiki.recyclerview_adapters.AlbumAdapter
import com.shivam.musicwiki.recyclerview_adapters.ArtistsAdapter
import com.shivam.musicwiki.recyclerview_adapters.GenreAdapter
import com.shivam.musicwiki.recyclerview_adapters.TracksAdapter


@BindingAdapter("bindgenrecyclerview")
fun bindGenreRecyclerView(view: RecyclerView, dataList: List<Tag>?) {

    val gridLayoutManager = GridLayoutManager(view.context,3)
    val layoutManager = view.layoutManager
    if (layoutManager == null) {
        view.layoutManager = gridLayoutManager
    }

    view.itemAnimator = DefaultItemAnimator()
//    view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))

    view.setHasFixedSize(true)

    var adapter: GenreAdapter? = view.adapter as GenreAdapter?

    if (adapter == null) {
        if (dataList != null) {
            adapter = GenreAdapter(view.context, dataList.toMutableList())
            view.adapter = adapter
        }
    }

    if (dataList != null) {
        adapter?.updateData(dataList)
    }
}


@BindingAdapter("bind_album_recyclerview")
fun bindAlbumRecyclerView(view: RecyclerView, dataList: List<AlbumDataClass>?) {

    val gridLayoutManager = GridLayoutManager(view.context,2)
    val layoutManager = view.layoutManager
    if (layoutManager == null) {
        view.layoutManager = gridLayoutManager
    }

    view.itemAnimator = DefaultItemAnimator()
//    view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))

    view.setHasFixedSize(true)

    var adapter: AlbumAdapter? = view.adapter as AlbumAdapter?

    if (adapter == null) {
        if (dataList != null) {
            adapter = AlbumAdapter(view.context, dataList.toMutableList())
            view.adapter = adapter
        }
    }

    if (dataList != null) {
        adapter?.updateData(dataList)
    }
}

@BindingAdapter("bind_artist_recyclerview")
fun bindArtistsRecyclerView(view: RecyclerView, dataList: List<ArtistsDataClass>?) {

    val gridLayoutManager = GridLayoutManager(view.context,2)
    val layoutManager = view.layoutManager
    if (layoutManager == null) {
        view.layoutManager = gridLayoutManager
    }

    view.itemAnimator = DefaultItemAnimator()
//    view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))

    view.setHasFixedSize(true)

    var adapter: ArtistsAdapter? = view.adapter as ArtistsAdapter?

    if (adapter == null) {
        if (dataList != null) {
            adapter = ArtistsAdapter(view.context, dataList.toMutableList())
            view.adapter = adapter
        }
    }

    if (dataList != null) {
        adapter?.updateData(dataList)
    }
}


@BindingAdapter("bind_track_recyclerview")
fun bindTracksRecyclerView(view: RecyclerView, dataList: List<TracksDataClass>?) {

    val gridLayoutManager = GridLayoutManager(view.context,2)
    val layoutManager = view.layoutManager
    if (layoutManager == null) {
        view.layoutManager = gridLayoutManager
    }

    view.itemAnimator = DefaultItemAnimator()
//    view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))

    view.setHasFixedSize(true)

    var adapter: TracksAdapter? = view.adapter as TracksAdapter?

    if (adapter == null) {
        if (dataList != null) {
            adapter = TracksAdapter(view.context, dataList.toMutableList())
            view.adapter = adapter
        }
    }

    if (dataList != null) {
        adapter?.updateData(dataList)
    }
}


