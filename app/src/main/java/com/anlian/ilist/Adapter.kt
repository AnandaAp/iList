package com.anlian.ilist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.getSystemService
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.anlian.ilist.databinding.MovieDetailBinding
import com.anlian.ilist.model.MovieResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Adapter(private val context: FragmentActivity, private var data: ArrayList<MovieResponse>?) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>
    inner class ViewHolder(val binding: MovieDetailBinding) : RecyclerView.ViewHolder (binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieDetailBinding
            .inflate(LayoutInflater
                .from(parent.context), parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("posisi = $position")
        val title = data?.get(position)?.title.toString()
        val popularity = data?.get(position)?.popularity.toString()
        val overview = data?.get(position)?.overview.toString()
        val poster = data?.get(position)?.posterPath.toString()
        holder.binding.movieTitle.text = title
        holder.binding.moviePopularity.text = "Popularity : $popularity"
        holder.binding.overviewText.text = overview
        holder.binding.cardView.setOnClickListener {
//            val intent = Intent(context,DetailActivity::class.java)
//            intent.putExtra("title",data?.get(position)?.title)
//            intent.putExtra("popularity",popularity)
//            intent.putExtra("overview",data?.get(position)?.overview)
//            intent.putExtra("poster",data?.get(position)?.posterPath)
//            context.startActivity(intent)
            val direction = ListMovieDirections.actionListMovieToDetailMovie(popularity,
                overview,poster,title)
//            sheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(it)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(_data : ArrayList<MovieResponse>){
        data?.clear()
        data = _data
        notifyDataSetChanged()
    }
}