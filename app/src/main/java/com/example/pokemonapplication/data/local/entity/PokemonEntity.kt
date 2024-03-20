package com.example.pokemonapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemonapplication.data.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>,
    val weight: Float,
    val height: Float
)

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        types = this.types,
        weight = this.weight,
        height = this.height
    )
}

