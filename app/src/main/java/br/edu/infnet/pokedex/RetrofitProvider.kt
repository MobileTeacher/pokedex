package br.edu.infnet.pokedex

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.baseUrl("http://infnet.educacao.ws/")
                .baseUrl("https://pokeapi.co/api/v2/")
                .build()