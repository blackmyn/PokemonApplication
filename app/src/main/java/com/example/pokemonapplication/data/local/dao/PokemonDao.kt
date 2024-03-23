package com.example.pokemonapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    fun getAllPokemons(): Flow<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetails(pokemonDetails: PokemonDetails)

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getPokemonDetails(id: Int): Flow<PokemonDetails?>
}