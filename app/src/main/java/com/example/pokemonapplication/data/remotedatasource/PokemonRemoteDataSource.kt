package com.example.pokemonapplication.data.remotedatasource

class PokemonRemoteDataSource(private val apiService: PokemonApiService) {

    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListResponse {
        return apiService.getPokemonList(offset, limit)
    }

    suspend fun getPokemonDetails(id: Int): PokemonDetailResponse {
        return apiService.getPokemonDetails(id)
    }
}