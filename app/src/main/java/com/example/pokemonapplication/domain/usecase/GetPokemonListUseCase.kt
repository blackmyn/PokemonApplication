package com.example.pokemonapplication.domain.usecase

import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val repository: PokemonRepository) {
    fun execute(offset: Int, limit: Int): Flow<List<Pokemon>> {
        return repository.getPokemonList(offset, limit)
    }
}