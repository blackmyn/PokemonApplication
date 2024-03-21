package com.example.pokemonapplication.data.remote

import android.content.Context
import androidx.room.Room
import com.example.pokemonapplication.data.local.dao.PokemonDao
import com.example.pokemonapplication.data.local.database.PokemonDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private var database: PokemonDatabase? = null

    fun providePokemonApiService(context: Context): PokemonApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java, "pokemon_database"
            ).build()
        }

        return retrofit.create(PokemonApiService::class.java)
    }

    fun providePokemonDao(context: Context): PokemonDao {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java, "pokemon_database"
            ).build()
        }
        return database!!.pokemonDao()
    }
}
