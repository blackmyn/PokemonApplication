package com.example.pokemonapplication.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokemonapplication.data.local.dao.PokemonDao
import com.example.pokemonapplication.data.model.Pokemon
import com.example.pokemonapplication.data.model.PokemonDetails

@Database(entities = [Pokemon::class, PokemonDetails::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var instance: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PokemonDatabase {
            return Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_database")
                .build()
        }
    }
}