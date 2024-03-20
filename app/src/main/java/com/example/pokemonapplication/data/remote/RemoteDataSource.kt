package com.example.pokemonapplication.data.remote

import com.example.pokemonapplication.data.model.PokemonDTO
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListResponse>
    suspend fun getPokemonDetails(pokemonId: Int): PokemonDTO
}
