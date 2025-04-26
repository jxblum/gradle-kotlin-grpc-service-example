plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:1.9.25")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
}

gradlePlugin {
    plugins {
        create("grpcPlugin") {
            id = "org.codeprimate.gradle.plugin.grpc"
            implementationClass = "org.codeprimate.gradle.plugins.GrpcPlugin"
        }
    }
}
