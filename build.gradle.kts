import org.codeprimate.gradle.plugins.GrpcPlugin

plugins {
    kotlin("jvm")
}

apply<GrpcPlugin>()

group="org.codeprimate.example"
version="0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.protobuf.java)
    implementation(libs.protobuf.java.util)
    implementation(libs.protobuf.kotlin)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.grpc.stub)
}
