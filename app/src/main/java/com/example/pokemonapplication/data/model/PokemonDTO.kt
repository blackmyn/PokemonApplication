package com.example.pokemonapplication.data.model

data class PokemonDTO(
    val id: Int,
    val name: String,
    val sprites: SpritesDTO,
    val types: List<TypeDTO>,
    val weight: Int,
    val height: Int
) {
    data class SpritesDTO(
        val front_default: String
    )

    data class TypeDTO(
        val type: TypeDetailsDTO
    ) {
        data class TypeDetailsDTO(
            val name: String
        )
    }
}