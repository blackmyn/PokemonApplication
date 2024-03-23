package com.example.pokemonapplication.presentation.mvi

import com.example.pokemonapplication.data.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemons: List<Pokemon> = emptyList(),
    val error: String? = null
)