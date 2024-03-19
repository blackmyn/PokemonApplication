package com.example.pokemonapplication.data.localdatasource

import com.example.pokemonapplication.data.models.Pokemon
import com.example.pokemonapplication.data.models.PokemonDetail

class PokemonLocalDataSource(private val database: PokemonDatabase) {

    suspend fun savePokemonList(pokemonList: List<Pokemon>) {
        database.pokemonDao().insertAll(pokemonList)
    }

    suspend fun savePokemonDetails(pokemonDetail: PokemonDetail) {
        database.pokemonDetailDao().insert(pokemonDetail)
    }
}
