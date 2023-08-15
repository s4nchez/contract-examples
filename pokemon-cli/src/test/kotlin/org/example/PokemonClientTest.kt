package org.example

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import kotlin.test.Test

class PokemonClientTest {
    @Test fun fetchPikachu() {
        val client = PokemonClient()
        assertThat(client.fetch("pikachu"), equalTo(PokemonDetails("pikachu", 4)))
    }
}
