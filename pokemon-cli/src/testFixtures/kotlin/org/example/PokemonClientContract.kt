package org.example

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.HttpHandler
import kotlin.test.Test


abstract class PokemonClientContract {
    abstract val client: HttpHandler

    @Test
    fun fetchPikachu() {
        val client = PokemonClient(client)
        assertThat(client.fetch("pikachu"), equalTo(PokemonDetails("pikachu", 4)))
    }
}