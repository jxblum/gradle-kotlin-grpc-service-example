pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version "1.9.25"
        id("com.google.protobuf") version "0.9.4"
    }
}

rootProject.name="gradle-kotlin-grpc-service-example"
