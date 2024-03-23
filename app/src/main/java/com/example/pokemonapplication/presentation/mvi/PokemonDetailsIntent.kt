package com.example.pokemonapplication.presentation.mvi

sealed class PokemonDetailsIntent {
    data class LoadPokemonDetails(val pokemonId: Int) : PokemonDetailsIntent()
}