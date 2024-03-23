package com.example.pokemonapplication.data.remote

import com.example.pokemonapplication.data.model.PokemonDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    data class PokemonListResponse(
        val count: Int,
        val next: String?,
        val previous: String?,
        val results: List<PokemonResult>
    )

    data class PokemonResult(
        val name: String,
        val url: String
    )
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): PokemonDetails
}