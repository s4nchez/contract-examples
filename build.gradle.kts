plugins {
    kotlin("jvm") version "1.9.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}


subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}
