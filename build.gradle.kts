import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jmailen.kotlinter") version "3.8.0"
    application
}

group = "dev.s7a.f"
version = "1.0.0"

repositories {
    mavenCentral()
}

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

dependencies {
    shadowImplementation(kotlin("stdlib-jdk8"))
    shadowImplementation("io.ktor:ktor-server-cio:2.0.0-beta-1")
    shadowImplementation("io.ktor:ktor-server-call-logging:2.0.0-beta-1")
    shadowImplementation("io.ktor:ktor-client-cio:2.0.0-beta-1")
    shadowImplementation("io.ktor:ktor-client-auth:2.0.0-beta-1")
    shadowImplementation("ch.qos.logback:logback-classic:1.2.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation)
    archiveClassifier.set("")
}

tasks.named("build") {
    dependsOn("shadowJar")
}

application {
    mainClass.set("dev.s7a.f.Main")
}
