plugins {
    id("com.google.cloud.tools.jib") version "3.3.2"
    application
}

dependencies {
    api(platform("org.http4k:http4k-bom:5.0.0.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")
}

application {
    mainClass.set("org.example.pokemon.LaunchServerKt")
}

jib.container.args = listOf("org.example.pokemon.LaunchServerKt")