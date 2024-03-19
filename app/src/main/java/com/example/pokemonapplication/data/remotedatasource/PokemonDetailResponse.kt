package com.example.pokemonapplication.data.remotedatasource

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonSpritesResponse,
    @SerializedName("types") val types: List<PokemonTypeResponse>,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int
)

data class PokemonSpritesResponse(
    @SerializedName("front_default") val frontDefault: String
)

data class PokemonTypeResponse(
    @SerializedName("type") val type: PokemonTypeDetailsResponse
)

data class PokemonTypeDetailsResponse(
    @SerializedName("name") val name: String
)
