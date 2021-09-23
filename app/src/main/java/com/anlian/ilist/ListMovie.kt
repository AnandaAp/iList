package com.anlian.ilist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anlian.ilist.api.NetworkConfig
import com.anlian.ilist.databinding.FragmentListMovieBinding
import com.anlian.ilist.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMovie : Fragment() {
    private lateinit var adapter: Adapter
    private lateinit var binding: FragmentListMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter = Adapter(requireActivity(), arrayListOf())
        binding.rvFr.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFr.adapter = adapter
        startRetrofit()

        // Inflate the layout for this fragment
        return view
    }

    private fun startRetrofit(){
        val api = NetworkConfig()
        api.getService().getPopularMovie()
            .enqueue(object : Callback<MovieResponse> {
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
                        Toast.makeText(
                            requireActivity(),
                            "Reponse Gagal",
                            Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    println("Failed to")
                    Toast.makeText(
                        requireActivity(),
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