package com.anlian.ilist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.navArgs
import com.anlian.ilist.databinding.FragmentDetailFilmBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailFilm : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDetailFilmBinding
    private val args: DetailFilmArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailFilmBinding.inflate(inflater, container, false)
        val view = binding.root
        val posterPath = "https://image.tmdb.org/t/p/w500${args.poster}"
        Glide
            .with(requireActivity())
            .load(posterPath)
            .into(binding.posterImg)
        binding.titleDetailFilm.text = args.title
        binding.popularityItm.text = "Popularity: \n${args.popularity}"
        binding.overviewDetailFilm.text = args.overview

        return view
    }
}