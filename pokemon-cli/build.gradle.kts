dependencies {
    api(platform("org.http4k:http4k-bom:5.0.0.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.http4k:http4k-testing-hamkrest:5.0.0.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.github.docker-java:docker-java:3.3.2")
}