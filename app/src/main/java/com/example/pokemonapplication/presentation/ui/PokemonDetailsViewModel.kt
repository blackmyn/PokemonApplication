package com.example.pokemonapplication.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapplication.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokemonapplication.domain.usecase.Resource
import com.example.pokemonapplication.presentation.mvi.PokemonDetailsIntent
import com.example.pokemonapplication.presentation.mvi.PokemonDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonDetailsState())
    val state: StateFlow<PokemonDetailsState> = _state

    fun processIntent(intent: PokemonDetailsIntent) {
        when (intent) {
            is PokemonDetailsIntent.LoadPokemonDetails -> loadPokemonDetails(intent.pokemonId)
        }
    }

    private fun loadPokemonDetails(pokemonId: Int) {
        getPokemonDetailsUseCase(pokemonId)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> _state.value = PokemonDetailsState(isLoading = true)
                    is Resource.Success -> _state.value =
                        PokemonDetailsState(pokemonDetails = result.data)

                    is Resource.Error -> _state.value = PokemonDetailsState(error = result.message)
                }
            }
            .launchIn(viewModelScope)
    }
}