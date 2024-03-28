package com.example.pokemonapplication.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.domain.usecase.GetPokemonsUseCase
import com.example.pokemonapplication.domain.usecase.Resource
import com.example.pokemonapplication.presentation.mvi.PokemonListIntent
import com.example.pokemonapplication.presentation.mvi.PokemonListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class PokemonListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state

    private val _intent = MutableSharedFlow<PokemonListIntent>()
    val intent: SharedFlow<PokemonListIntent> = _intent
    private var currentPage = 1
    private var hasMorePages = true
    init {
        viewModelScope.launch {
            _intent
                .flatMapConcat { intent ->
                    when (intent) {
                        is PokemonListIntent.LoadPokemons -> loadPokemons(currentPage)
                        is PokemonListIntent.SelectPokemon -> selectPokemon(intent.pokemon)
                    }
                }
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> _state.value = PokemonListState(isLoading = true)
                        is Resource.Success -> _state.value =
                            PokemonListState(pokemons = result.data ?: emptyList())

                        is Resource.Error -> _state.value = PokemonListState(error = result.message)
                    }
                }
        }
    }

    private fun loadPokemons(page: Int): Flow<Resource<List<Pokemon>>> {
        return getPokemonsUseCase(20, 0)
            .onEach {
                if (it is Resource.Success) {
                    hasMorePages = it.data?.isNotEmpty() ?: false
                }
            }
    }

    fun loadNextPage() {
        if (hasMorePages) {
            currentPage++
            sendIntent(PokemonListIntent.LoadPokemons)
        }
    }

    fun loadPreviousPage() {
        if (currentPage > 1) {
            currentPage--
            sendIntent(PokemonListIntent.LoadPokemons)
        }
    }

    private fun selectPokemon(pokemon: Pokemon): Flow<Resource<List<Pokemon>>> {
        return flow { emit(Resource.Success(emptyList())) }
    }

    fun sendIntent(intent: PokemonListIntent) {
        viewModelScope.launch { _intent.emit(intent) }
    }
}