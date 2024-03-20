package com.example.pokemonapplication.data.repository

import com.example.pokemonapplication.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(offset: Int, limit: Int): Flow<List<Pokemon>>
    suspend fun getPokemonDetails(pokemonId: Int): Pokemon
}
