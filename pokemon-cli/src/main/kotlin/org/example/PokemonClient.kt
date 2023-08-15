package org.example

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.format.Jackson.auto

class PokemonClient(private val client: HttpHandler) {
    private val lens = Body.auto<PokemonDetails>().toLens()

    fun fetch(pokemonName: String) =
        lens(client(Request(Method.GET, "/$pokemonName")))
}

data class PokemonDetails(val name: String, val height: Int)