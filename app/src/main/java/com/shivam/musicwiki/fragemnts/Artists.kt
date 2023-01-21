package com.shivam.musicwiki.fragemnts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shivam.musicwiki.GenreDetailsActivity
import com.shivam.musicwiki.R
import com.shivam.musicwiki.databinding.FragmentAlbumsBinding
import com.shivam.musicwiki.databinding.FragmentArtistsBinding

class Artists : Fragment() {

    private lateinit var mViewModel: ArtistsViewModel
    private lateinit var binding: FragmentArtistsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistsBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ArtistsViewModel::class.java]

        val genre = (activity as GenreDetailsActivity?)?.getGenre()
        if (genre != null) {
            genre.name?.let { mViewModel.loadArtists(it) }
        }
        mViewModel.getArtists().observe(viewLifecycleOwner) { binding.dataList = it }

        return binding.root

    }
}