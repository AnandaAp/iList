package com.anlian.ilist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anlian.ilist.api.NetworkConfig
import com.anlian.ilist.databinding.ActivityMainBinding
import com.anlian.ilist.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val MovieDBApiToken = "9b0be6c15ab706d1a34253ea1f223df9"
    private lateinit var popularMovieStr: String
    private val popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=$MovieDBApiToken"
    private var popularMovie = arrayListOf<MovieResponse>()
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter = Adapter(this,arrayListOf())
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter
        startRetrofit()

    }
    private fun startRetrofit(){
        val api = NetworkConfig()
        api.getService().getPopularMovie()
            .enqueue(object : Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()?.results
                        showData(result)
                        println(result)
                        println("ok")
                    }else{
                        Toast.makeText(this@MainActivity, "Reponse Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    println("Failed to")
                    Toast.makeText(
                        this@MainActivity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun showData(result: List<MovieResponse?>?) {
        updateAdapter(result)
    }

    private fun updateAdapter(result: List<MovieResponse?>?) {
        adapter.setData(result as ArrayList<MovieResponse>)
    }
}
