import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    // ULID
    implementation("com.github.f4b6a3:ulid-creator:5.2.2")
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}