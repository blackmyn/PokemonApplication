package com.example.pokemonapplication.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    fun loadPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val pokemonDetails = repository.getPokemonDetails(pokemonId)
                _pokemon.value = pokemonDetails
            } catch (e: Exception) {
            }
        }
    }
}