package org.example.pokemon

import org.http4k.core.Body
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.format.Jackson.auto

fun PokemonServer() = { _: Request ->
    val data = Body.auto<PokemonDetails>().toLens()
    Response(OK).with(data of PokemonDetails("pikachu", 3))
}

data class PokemonDetails(val name: String, val height: Int)