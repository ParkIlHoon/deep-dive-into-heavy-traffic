import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
}

group = "dev.hoon.deep-dive.heavy-traffic"
version = "unspecified"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // ULID
    implementation("com.github.f4b6a3:ulid-creator:5.2.2")
}

tasks.test {
    useJUnitPlatform()
}