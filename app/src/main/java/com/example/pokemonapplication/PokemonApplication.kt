package com.example.pokemonapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.pokemonapplication.data.local.database.PokemonDatabase

class PokemonApplication : AppCompatActivity() {
    lateinit var database: PokemonDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java, "pokemon_database"
        ).build()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }
}
