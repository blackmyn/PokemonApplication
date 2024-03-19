package com.example.pokemonapplication.data.models

data class PokemonDetail(
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val weight: Float,
    val height: Float
)
