package com.anlian.ilist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anlian.ilist.databinding.FragmentDetailMovieBinding
import com.bumptech.glide.Glide

class DetailMovie : Fragment() {
    private lateinit var binding: FragmentDetailMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val title = requireActivity().intent.getStringExtra("title")
        val popular = requireActivity().intent.getStringExtra("popularity")
        val poster = requireActivity().intent.getStringExtra("poster")
        val overview = requireActivity().intent.getStringExtra("overview")
        if (popular != null) {
            processData(title,popular, poster, overview)
        }
        return binding.root
    }

    private fun processData(
        title: String?,
        popular: String,
        poster: String?,
        overview: String?
    ) {
        val posterPath = "https://image.tmdb.org/t/p/w500$poster"
        binding.titleFilm.text = title
        binding.popularityFIlm.text = "${requireActivity().getString(R.string.popularity)} : $popular"
        binding.overviewFilm.text = overview
        Glide
            .with(requireActivity())
            .load(posterPath)
            .into(binding.poster)
        println(posterPath)
    }
}