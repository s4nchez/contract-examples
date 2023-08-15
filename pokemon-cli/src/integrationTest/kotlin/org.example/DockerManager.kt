package org.example

import com.github.dockerjava.api.command.PullImageResultCallback
import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientBuilder
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request
import java.io.IOException
import java.util.concurrent.TimeUnit

class DockerManager {
    private val registryUsername = System.getenv()["REGISTRY_USERNAME"] ?: error("REGISTRY_USERNAME missing")
    private val registryPassword = System.getenv()["REGISTRY_PASSWORD"] ?: error("REGISTRY_PASSWORD missing")

    private val config = DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withRegistryUsername(registryUsername)
        .withRegistryPassword(registryPassword)
        .build()

    private val dockerClient = DockerClientBuilder.getInstance(config).build()
    private var containerId: String? = null

    fun startServer() {
        dockerClient.pullImageCmd("ghcr.io/s4nchez/pokemon-api")
            .withTag("latest")
            .exec(PullImageResultCallback())
            .awaitCompletion(30, TimeUnit.SECONDS);

        containerId = dockerClient.createContainerCmd("ghcr.io/s4nchez/pokemon-api:latest")
            .withExposedPorts(ExposedPort.tcp(8080))
            .withPortSpecs("8080/tcp")
            .withHostConfig(
                HostConfig.newHostConfig().withPortBindings(PortBinding.parse("8080:8080"))
            )
            .exec().id

        dockerClient.startContainerCmd(containerId!!).exec()

        var alive = false
        var attempts = 0
        do {
            attempts += 1
            try {
                alive = JavaHttpClient()(Request(Method.GET, "http://localhost:8080")).status.successful
            } catch (exception: IOException) {

            }
            Thread.sleep(250)
        } while (!alive)

        if (!alive) error("Server is not alive after $attempts attempts")
    }

    fun stopServer() {
        if (containerId != null) {
            dockerClient.killContainerCmd(containerId!!).exec()
            dockerClient.removeContainerCmd(containerId!!).exec()
        }

    }
}