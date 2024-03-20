package com.example.pokemonapplication.data.local

import android.content.Context
import androidx.room.Room
import com.example.pokemonapplication.data.local.dao.PokemonDao
import com.example.pokemonapplication.data.local.database.PokemonDatabase
import com.example.pokemonapplication.data.local.entity.PokemonEntity

class LocalDataSource(context: Context) {
    private val pokemonDatabase: PokemonDatabase = Room.databaseBuilder(
        context.applicationContext,
        PokemonDatabase::class.java, "pokemon_database"
    ).build()

    val pokemonDao: PokemonDao = pokemonDatabase.pokemonDao()

    suspend fun savePokemonList(pokemonList: List<PokemonEntity>) {
        pokemonDao.savePokemonList(pokemonList)
    }

    suspend fun getAllPokemon(): List<PokemonEntity> {
        return pokemonDao.getAllPokemon()
    }

    suspend fun getPokemonById(id: Int): PokemonEntity? {
        return pokemonDao.getPokemonById(id)
    }
}