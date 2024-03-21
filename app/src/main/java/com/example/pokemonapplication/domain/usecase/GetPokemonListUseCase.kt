package com.example.pokemonapplication.domain.usecase

import com.example.pokemonapplication.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetPokemonListUseCase {
    fun execute(offset: Int, limit: Int): Flow<List<Pokemon>>
}
