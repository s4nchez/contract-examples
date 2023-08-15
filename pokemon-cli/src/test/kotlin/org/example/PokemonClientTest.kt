package org.example

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import kotlin.test.Test

class PokemonClientTest {
    @Test
    fun fetchPikachu() {
        val fakeServer = { _: Request ->
            val data = Body.auto<PokemonDetails>().toLens()
            Response(Status.OK).with(data of PokemonDetails("pikachu", 4))
        }

        val client = PokemonClient(fakeServer)
        assertThat(client.fetch("pikachu"), equalTo(PokemonDetails("pikachu", 4)))
    }
}
