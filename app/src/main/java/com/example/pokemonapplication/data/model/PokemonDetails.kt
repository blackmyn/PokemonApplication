package com.example.pokemonapplication.data.model

import androidx.room.Entity

@Entity(tableName = "pokemonsdetails")
data class PokemonDetails(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int,
    val height: Int
)

data class Sprites(
    val front_default: String?
)

data class Type(
    val slot: Int,
    val type: TypeDetails
)

data class TypeDetails(
    val name: String
)