package com.example.pokemonapplication.presentation.mvi

import com.example.pokemonapplication.data.model.PokemonDetails

data class PokemonDetailsState(
    val isLoading: Boolean = false,
    val pokemonDetails: PokemonDetails? = null,
    val error: String? = null
)