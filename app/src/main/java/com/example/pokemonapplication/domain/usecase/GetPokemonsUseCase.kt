package com.example.pokemonapplication.domain.usecase

import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    private var currentPage = 1

    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        try {
            val pokemons = pokemonRepository.getPokemonList(limit, offset).first()
            emit(Resource.Success(pokemons))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unknown error occurred."))
        }
    }

    fun loadNextPage() {
        currentPage++
    }

    fun loadPreviousPage() {
        if (currentPage > 1) {
            currentPage--
        }
    }

    fun getCurrentOffset(): Int {
        return (currentPage - 1) * 20 // Assuming a limit of 20 items per page
    }
}