package com.example.pokemonapplication.data.model

data class PokemonDetail(
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val weight: Float,
    val height: Float
)
