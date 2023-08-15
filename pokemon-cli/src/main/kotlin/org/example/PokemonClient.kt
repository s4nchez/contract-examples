package org.example

class PokemonClient {

    fun fetch(pokemonName: String): PokemonDetails = PokemonDetails("pikachu", 4)
}

data class PokemonDetails(val name: String, val height: Int)