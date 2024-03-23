package com.example.pokemonapplication.presentation.mvi

import com.example.pokemonapplication.data.model.Pokemon

sealed class PokemonListIntent {
    object LoadPokemons : PokemonListIntent()
    data class SelectPokemon(val pokemon: Pokemon) : PokemonListIntent()
}