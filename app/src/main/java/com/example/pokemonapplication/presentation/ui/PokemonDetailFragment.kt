package com.example.pokemonapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemonapplication.R
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.presentation.model.PokemonDetailViewModel
import com.example.pokemonapplication.presentation.model.PokemonDetailViewState

class PokemonDetailFragment : Fragment() {

    private lateinit var viewModel: PokemonDetailViewModel

    private lateinit var imageViewPokemon: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewTypes: TextView
    private lateinit var textViewWeight: TextView
    private lateinit var textViewHeight: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
        setupViews(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonDetailViewModel::class.java)
        observeViewModel()
        val pokemonId = arguments?.getString("pokemonId") ?: ""
        viewModel.loadPokemonDetails(pokemonId.toInt())
    }

    private fun setupViews(view: View) {
        imageViewPokemon = view.findViewById(R.id.imageViewPokemon)
        textViewName = view.findViewById(R.id.textViewName)
        textViewTypes = view.findViewById(R.id.textViewTypes)
        textViewWeight = view.findViewById(R.id.textViewWeight)
        textViewHeight = view.findViewById(R.id.textViewHeight)

        view.findViewById<View>(R.id.buttonBack).setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PokemonDetailViewState.Loading -> {
                    // Show loading progress bar
                }

                is PokemonDetailViewState.Success -> {
                    state.pokemon?.let { pokemon ->
                        showPokemonDetails(pokemon)
                    }
                }

                is PokemonDetailViewState.Error -> {
                    // Handle error state
                }
            }
        }
    }

    private fun showPokemonDetails(pokemon: Pokemon) {
        Glide.with(requireContext())
            .load(pokemon.imageUrl)
            .into(imageViewPokemon)
        textViewName.text = pokemon.name
        textViewTypes.text = pokemon.types.joinToString(", ")
        textViewWeight.text = getString(R.string.pokemon_weight, pokemon.weight)
        textViewHeight.text = getString(R.string.pokemon_height, pokemon.height)
    }
}