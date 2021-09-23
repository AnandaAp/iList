package com.anlian.ilist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.anlian.ilist.databinding.MovieDetailBinding
import com.anlian.ilist.model.MovieResponse

class Adapter(private val context: Context, private var data: ArrayList<MovieResponse>?) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(val binding: MovieDetailBinding) : RecyclerView.ViewHolder (binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieDetailBinding
            .inflate(LayoutInflater
                .from(parent.context), parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("posisi = $position")
        holder.binding.movieTitle.text = data?.get(position)?.title
        val popularity = data?.get(position)?.popularity.toString()
        holder.binding.moviePopularity.text = "Popularity : $popularity"
        holder.binding.overviewText.text = data?.get(position)?.overview
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("title",data?.get(position)?.title)
            intent.putExtra("popularity",popularity)
            intent.putExtra("overview",data?.get(position)?.overview)
            intent.putExtra("poster",data?.get(position)?.posterPath)
            context.startActivity(intent)
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