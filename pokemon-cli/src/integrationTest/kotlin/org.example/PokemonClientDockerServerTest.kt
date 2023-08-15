package org.example

import org.http4k.client.JavaHttpClient
import org.http4k.core.Filter
import org.http4k.core.NoOp
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.DebuggingFilters
import org.http4k.filter.inIntelliJOnly
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll


class PokemonClientDockerServerTest : PokemonClientContract() {
    override val client = Filter.NoOp.then(DebuggingFilters.PrintRequestAndResponse().inIntelliJOnly())
        .then(ClientFilters.SetBaseUriFrom(Uri.of("http://localhost:8080"))).then(JavaHttpClient())

    companion object {
        private val dockerManager = DockerManager()

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            dockerManager.startServer()
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            dockerManager.stopServer()
        }
    }
}
