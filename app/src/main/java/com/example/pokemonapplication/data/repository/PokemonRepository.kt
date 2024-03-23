package com.example.pokemonapplication.data.repository

import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<List<Pokemon>>
    fun getPokemonDetails(id: Int): Flow<PokemonDetails?>
}
