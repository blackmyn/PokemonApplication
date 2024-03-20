package com.example.pokemonapplication.domain.usecase

import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.repository.PokemonRepository

class GetPokemonDetailsUseCase(private val repository: PokemonRepository) {
    suspend fun execute(pokemonId: Int): Pokemon {
        return repository.getPokemonDetails(pokemonId)
    }
}