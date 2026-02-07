package com.example.retrofitexample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.R
import com.example.retrofitexample.data.PeliculasState
import com.example.retrofitexample.ui.adapters.PeliculasAdapter
import com.example.retrofitexample.ui.viewmodels.PeliculasViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: PeliculasViewModel by viewModels()
    private lateinit var peliculasAdapter: PeliculasAdapter
    private lateinit var txtConexion: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var rvMovies: RecyclerView
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtConexion = findViewById(R.id.txtError)
        progressBar = findViewById(R.id.paginationProgressBar)
        rvMovies = findViewById(R.id.rvMovies)

        peliculasAdapter = PeliculasAdapter()
        rvMovies.adapter = peliculasAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        viewModel.peliculas.observe(this) { response ->
            when (response) {
                is PeliculasState.Success -> {
                    hideSinConexion()
                    hideProgressBar()
                    response.datos?.let { newsResponse ->
                        peliculasAdapter.differ.submitList(newsResponse)
                    }
                }

                is PeliculasState.Error -> {
                    hideProgressBar()
                    showSinConexion()
                    response.mensaje?.let { mensaje ->
                        Log.e(TAG, "Ocurrió un error: $mensaje")
                    }
                }

                is PeliculasState.Loading -> {
                    hideSinConexion()
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun showSinConexion() {
        txtConexion.visibility = View.VISIBLE
    }

    private fun hideSinConexion() {
        txtConexion.visibility = View.INVISIBLE
    }
}
