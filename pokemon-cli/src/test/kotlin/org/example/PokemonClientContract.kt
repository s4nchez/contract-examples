package org.example

import org.http4k.core.Request
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import kotlin.test.Test


abstract class PokemonClientContract {
    abstract val client: HttpHandler

    @Test
    fun fetchPikachu() {
        val client = PokemonClient(client)
        assertThat(client.fetch("pikachu"), equalTo(PokemonDetails("pikachu", 4)))
    }
}