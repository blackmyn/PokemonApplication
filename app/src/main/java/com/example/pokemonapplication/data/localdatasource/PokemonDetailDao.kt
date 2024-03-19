package com.example.pokemonapplication.data.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonapplication.data.models.PokemonDetail
import com.example.pokemonapplication.data.models.PokemonDetailEntity

@Dao
interface PokemonDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonDetail: PokemonDetail)

    @Query("SELECT * FROM pokemon_details WHERE id = :id")
    suspend fun getPokemonDetailById(id: Int): PokemonDetailEntity?
}
