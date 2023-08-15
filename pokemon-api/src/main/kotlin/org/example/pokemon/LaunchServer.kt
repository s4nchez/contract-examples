package org.example.pokemon

import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    val port = System.getenv()["PORT"]?.toInt() ?: 8080
    PokemonServer().asServer(SunHttp(port)).start()
    println("Server started on port $port")
}