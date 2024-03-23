package com.example.pokemonapplication.data.local

import com.example.pokemonapplication.data.local.dao.PokemonDao
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val pokemonDao: PokemonDao) {

    fun getAllPokemon(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemons()
    }

    fun getPokemonById(id: Int): Flow<PokemonDetails?> {
        return pokemonDao.getPokemonDetails(id)
    }

    suspend fun insertPokemon(pokemon: List<Pokemon>) {
        pokemonDao.insertPokemons(pokemon)
    }

    suspend fun insertPokemonDetails(pokemon: PokemonDetails) {
        pokemonDao.insertPokemonDetails(pokemon)
    }
}