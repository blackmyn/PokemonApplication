package com.example.pokemonapplication.presentation.model

import com.example.pokemonapplication.data.model.Pokemon

sealed class PokemonDetailViewState {
    object Loading : PokemonDetailViewState()
    data class Success(val pokemon: Pokemon) : PokemonDetailViewState()
    data class Error(val message: String) : PokemonDetailViewState()
}
