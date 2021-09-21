package com.anlian.ilist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anlian.ilist.databinding.MovieDetailBinding
import com.anlian.ilist.model.MovieResponse

class Adapter(private var data: ArrayList<MovieResponse>?) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(val binding: MovieDetailBinding): RecyclerView.ViewHolder (binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieDetailBinding
            .inflate(LayoutInflater
                .from(parent.context), parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("posisi = $position")
        holder.binding.movieTitle.text = data?.get(position)?.title
        holder.binding.moviePopularity.text = "Popularity : ${data?.get(position)?.popularity.toString()}"
        holder.binding.overviewText.text = data?.get(position)?.overview
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