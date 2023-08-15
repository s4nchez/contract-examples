plugins {
    `jvm-test-suite`
    `java-test-fixtures`
}

dependencies {
    api(platform("org.http4k:http4k-bom:5.0.0.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")
    testFixturesImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testFixturesImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testFixturesImplementation("org.http4k:http4k-testing-hamkrest:5.0.0.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        val integrationTest by registering(JvmTestSuite::class) {
            useJUnitJupiter()
            testType.set(TestSuiteType.INTEGRATION_TEST)
            dependencies {
                implementation(project())
                implementation(testFixtures(project()))
                implementation("org.http4k:http4k-core")
                implementation("org.http4k:http4k-format-jackson")
                implementation("com.github.docker-java:docker-java:3.3.2")
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}