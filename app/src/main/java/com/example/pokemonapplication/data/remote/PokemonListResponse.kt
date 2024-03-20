package com.example.pokemonapplication.data.remote

import com.example.pokemonapplication.data.model.PokemonDTO

data class PokemonListResponse(
    val results: List<PokemonDTO>
)
