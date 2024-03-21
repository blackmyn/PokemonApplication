package com.example.pokemonapplication.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {

    private companion object {
        const val LIMIT = 20 // Define your desired limit
        const val OFFSET = 0 // Define your initial offset
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Success(val pokemonList: List<Pokemon>) : ViewState()
        data class Error(val message: String) : ViewState()
    }

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            try {
                repository.getPokemonList(limit = LIMIT, offset = OFFSET)
                    .collect { pokemonList ->
                        _viewState.value = ViewState.Success(pokemonList)
                    }
            } catch (e: Exception) {
                _viewState.value = ViewState.Error("Failed to fetch Pokemon list: ${e.message}")
            }
        }
    }
}