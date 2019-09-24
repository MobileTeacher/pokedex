package br.edu.infnet.pokedex

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    //@GET("dadosAtividades.php")
    @GET("pokemon/?offset=20&limit=20")
    fun getRawPokemons(): Call<ResponseBody>

    @GET("pokemon")
    fun getPokemons(@Query("offset") offset: Int = 0,
                    @Query("limit") limit: Int = 20): Call<PokemonResult>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") number: Int): Call<ResponseBody>

}