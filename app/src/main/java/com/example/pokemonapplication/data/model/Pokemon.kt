package com.example.pokemonapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class Pokemon(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String
)