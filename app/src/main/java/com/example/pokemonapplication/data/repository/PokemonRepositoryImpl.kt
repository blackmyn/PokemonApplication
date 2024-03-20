package com.example.pokemonapplication.data.repository

import com.example.pokemonapplication.data.local.LocalDataSource
import com.example.pokemonapplication.data.local.entity.PokemonEntity
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.model.PokemonDTO
import com.example.pokemonapplication.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : PokemonRepository {

    override fun getPokemonList(offset: Int, limit: Int): Flow<List<Pokemon>> = flow {
        val cachedPokemon = localDataSource.getAllPokemon()
        if (cachedPokemon.isNotEmpty()) {
            emit(cachedPokemon.map { it.toPokemon() })
        }

        val response = remoteDataSource.getPokemonList(offset, limit)
        if (response.isSuccessful) {
            val pokemonDTOList = response.body()?.results ?: emptyList()
            val pokemonList = pokemonDTOList.map { it.toPokemonEntity() }
            localDataSource.savePokemonList(pokemonList)
            emit(pokemonList.map { it.toPokemon() })
        } else {
            throw Exception("Error fetching Pokemon list")
        }
    }

    override suspend fun getPokemonDetails(pokemonId: Int): Pokemon {
        val cachedPokemon = localDataSource.getPokemonById(pokemonId)
        if (cachedPokemon != null) {
            return cachedPokemon.toPokemon()
        }

        val pokemonDTO = remoteDataSource.getPokemonDetails(pokemonId)
        return pokemonDTO.toPokemonEntity().toPokemon()
    }
}

private fun PokemonDTO.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        imageUrl = sprites.frontDefault ?: "",
        types = types.map { it.type.name },
        weight = weight,
        height = height
    )
}


private fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
        types = types,
        weight = weight,
        height = height
    )
}
