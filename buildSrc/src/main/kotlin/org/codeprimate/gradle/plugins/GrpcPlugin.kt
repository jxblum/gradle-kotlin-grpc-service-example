package org.codeprimate.gradle.plugins

import com.google.protobuf.gradle.ProtobufExtension
import com.google.protobuf.gradle.id
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

const val JDK_VERSION = "17"

/**
 * Example Gradle [Plugin] used to configure Google's Protobuf [Plugin] and gRPC.
 *
 * @author John Blum
 * @see Plugin
 * @see Project
 * @since 0.1.0
 */
class GrpcPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        configureKotlin(project)
        configureProtobufWithGrpc(project)
    }

    private fun applyPlugins(project: Project) {
        project.pluginManager.apply("org.jetbrains.kotlin.jvm")
        project.pluginManager.apply("com.google.protobuf")
    }

    private fun configureKotlin(project: Project) {

        val jdkVersion = getJdkVersion(project)

        project.kotlinExtension.jvmToolchain(jdkVersion.toInt())

        project.tasks.withType(KotlinCompile::class.java) {
            kotlinOptions {
                freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
            }
        }
    }

    private fun configureProtobufWithGrpc(project: Project) {

        project.extensions.configure<ProtobufExtension> {
            protoc{
                artifact = "com.google.protobuf:protoc:4.30.2"
            }
            plugins {
                id("grpc") {
                    artifact = "io.grpc:protoc-gen-grpc-java:1.72.0"
                }
                id("grpckt") {
                    artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.3:jdk8@jar"
                }
            }
            generateProtoTasks {
                all().forEach {
                    it.plugins {
                        id("grpc")
                        id("grpckt")
                    }
                    it.builtins {
                        id("kotlin")
                    }
                }
            }
        }
    }

    private fun getProperty(project: Project, propertyName: String): String? = System.getProperty(propertyName)
        ?: project.findProperty(propertyName) as String?

    private fun getProperty(project: Project, propertyName: String, defaultValue: String): String =
        getProperty(project, propertyName) ?: defaultValue

    private fun getJdkVersion(project: Project): String =
        getProperty(project, "jdkVersion", JDK_VERSION)

}
