package br.edu.infnet.pokedex

class Pokemon(val name: String,
              val url: String){
    override fun toString() = "$name"
}

class PokemonResult(val results: List<Pokemon>)