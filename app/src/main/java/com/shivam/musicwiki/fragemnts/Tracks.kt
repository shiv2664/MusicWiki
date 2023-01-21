package com.shivam.musicwiki.fragemnts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shivam.musicwiki.GenreDetailsActivity
import com.shivam.musicwiki.R
import com.shivam.musicwiki.databinding.FragmentArtistsBinding
import com.shivam.musicwiki.databinding.FragmentTracksBinding

class Tracks : Fragment() {

    private lateinit var mViewModel: TracksViewModel
    private lateinit var binding: FragmentTracksBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTracksBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[TracksViewModel::class.java]

        val genre = (activity as GenreDetailsActivity?)?.getGenre()
        if (genre != null) {
            genre.name?.let { mViewModel.loadTracks(it) }
        }
        mViewModel.getTracks().observe(viewLifecycleOwner) {
            binding.dataList = it }

        return binding.root
    }


}