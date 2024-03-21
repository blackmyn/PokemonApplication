package com.example.pokemonapplication.domain.usecase

import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCaseImpl(private val repository: PokemonRepository) : GetPokemonListUseCase {
    override fun execute(offset: Int, limit: Int): Flow<List<Pokemon>> {
        return repository.getPokemonList(offset, limit)
    }
}
