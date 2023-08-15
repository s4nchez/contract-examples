package org.example

import org.http4k.core.*
import org.http4k.format.Jackson.auto


class PokemonClientFakeServerTest : PokemonClientContract() {
    private val fakeServer = { _: Request ->
        val data = Body.auto<PokemonDetails>().toLens()
        Response(Status.OK).with(data of PokemonDetails("pikachu", 4))
    }

    override val client = fakeServer
}
