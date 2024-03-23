package com.example.pokemonapplication.data.repository

import com.example.pokemonapplication.data.local.LocalDataSource
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.model.PokemonDetails
import com.example.pokemonapplication.data.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override fun getPokemonList(limit: Int, offset: Int): Flow<List<Pokemon>> = flow {
        val localPokemons = localDataSource.getAllPokemon().first()
        if (localPokemons.isNotEmpty()) {
            emit(localPokemons)
        } else {
            val response = remoteDataSource.getPokemons(limit, offset)
            val pokemons = response.results.map {
                Pokemon(
                    id = it.url.split("/")[6].toInt(),
                    name = it.name,
                    url = it.url
                )
            }
            localDataSource.insertPokemon(pokemons)
            emit(pokemons)
        }
    }


    override fun getPokemonDetails(id: Int): Flow<PokemonDetails?> = flow {
        val localPokemon = localDataSource.getPokemonById(id).first()
        if (localPokemon != null) {
            emit(localPokemon)
        } else {
            val pokemonDetails = remoteDataSource.getPokemonDetails(id)
            val pokemon = PokemonDetails(
                id = pokemonDetails.id,
                name = pokemonDetails.name,
                height = pokemonDetails.height,
                weight = pokemonDetails.weight,
                sprites = pokemonDetails.sprites,
                types = pokemonDetails.types
            )
            localDataSource.insertPokemonDetails(pokemon)
            emit(pokemon)
        }
    }
}