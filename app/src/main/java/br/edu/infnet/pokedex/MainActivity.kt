package br.edu.infnet.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonClient: PokemonAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // solicita uma implementação da interface PokemonAPI
        pokemonClient = retrofit.create(PokemonAPI::class.java)

        setUpListeners()
    }

    private fun setUpListeners(){
        access_button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            // recebo alguém que sabe realizar uma tarefa assíncrona
            // de acesso à URL desejada
            val start = start_edittext.offset()
            val end = end_edittext.limit()

            val call = pokemonClient.getPokemons(start, end)

            call.enqueue(object : Callback<PokemonResult>{
                override fun onFailure(call: Call<PokemonResult>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    content_textview.text = "FALHOU: ${t.message}"
                }
                override fun onResponse(call: Call<PokemonResult>,
                                        response: Response<PokemonResult>) {
                    progressBar.visibility = View.GONE

                    val pokemonResult = response.body()
                    if (pokemonResult != null){
                        val pokemonsList = pokemonResult.results
                        var content = ""
                        for (pokemon in pokemonsList){
                            content += "$pokemon\n"
                        }
                        content_textview.text = content
                    }else {
                        content_textview.text = "FALHOU com código: ${response.code()}"
                    }

                }


            })

//            call.enqueue(object: Callback<Pokemon>{
//                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
//                    progressBar.visibility = View.GONE
//                    content_textview.text = "FALHOU: ${t.message}"
//                }
//
//                override fun onResponse(call: Call<Pokemon>,
//                                        response: Response<Pokemon>) {
//                    progressBar.visibility = View.GONE
//
//                    response.body()?.let {
//                        content_textview.text = it.string()
//                    } ?: run {
//                        content_textview.text = "FALHOU com código: ${response.code()}"
//                    }
//
//                }
//            })
        }
    }

}
