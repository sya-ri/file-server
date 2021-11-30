import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("org.jmailen.kotlinter") version "3.7.0"
    application
}

group = "dev.s7a.f"
version = "0.1.0"

repositories {
    mavenCentral()
}

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

dependencies {
    shadowImplementation(kotlin("stdlib-jdk8"))
    shadowImplementation("io.ktor:ktor-server-cio:1.6.6")
    shadowImplementation("io.ktor:ktor-client-cio:1.6.6")
    shadowImplementation("io.ktor:ktor-client-auth:1.6.6")
    shadowImplementation("ch.qos.logback:logback-classic:1.2.7")
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
