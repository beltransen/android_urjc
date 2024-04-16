package com.example.retrofitexample.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.data.PeliculasState
import com.example.retrofitexample.data.repositories.PeliculasRepository
import kotlinx.coroutines.launch

class PeliculasViewModel: ViewModel() {
    private val repository = PeliculasRepository()
    private var _peliculas: MutableLiveData<PeliculasState> =  MutableLiveData()
    val peliculas: LiveData<PeliculasState>
        get() = _peliculas

    init {
        getAll()
    }

     fun getAll(){
         viewModelScope.launch {
             _peliculas.postValue(repository.getAllPeliculas())
         }
    }
}