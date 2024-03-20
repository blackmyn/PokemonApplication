package com.example.pokemonapplication.data.model

data class PokemonDTO(
    val id: Int,
    val name: String,
    val sprites: SpritesDTO,
    val types: List<TypeDTO>,
    val weight: Float,
    val height: Float
)

