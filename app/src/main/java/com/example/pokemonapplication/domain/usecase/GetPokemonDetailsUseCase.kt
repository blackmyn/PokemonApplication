package com.example.pokemonapplication.domain.usecase

import com.example.pokemonapplication.data.model.PokemonDetails
import com.example.pokemonapplication.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(id: Int): Flow<Resource<PokemonDetails?>> = flow {
        emit(Resource.Loading())
        try {
            val pokemon = pokemonRepository.getPokemonDetails(id).first()
            emit(Resource.Success(pokemon))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unknown error occurred."))
        }
    }
}