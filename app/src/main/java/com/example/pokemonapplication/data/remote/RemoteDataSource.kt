package com.example.pokemonapplication.data.remote

import com.example.pokemonapplication.data.model.PokemonDetails
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val pokemonApi: PokemonApiService
) {

    suspend fun getPokemons(limit: Int, offset: Int): PokemonApiService.PokemonListResponse {
        return pokemonApi.getPokemonList(limit, offset)
    }

    suspend fun getPokemonDetails(id: Int): PokemonDetails {
        return pokemonApi.getPokemonDetails(id)
    }
}