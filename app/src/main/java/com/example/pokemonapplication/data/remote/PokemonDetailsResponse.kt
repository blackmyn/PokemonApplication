package com.example.pokemonapplication.data.remote

data class PokemonDetailsResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
    val types: List<Type>
)

data class Sprites(
    val front_default: String
)

data class Type(
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)
