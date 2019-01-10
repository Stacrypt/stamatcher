import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion = "1.1.1"

plugins {
    kotlin("jvm") version "1.3.11"
}

group = "com.stacrypt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.fasterxml.jackson.core:jackson-core:jar:2.9.8")
    compile("io.ktor:ktor-server-core:$ktorVersion")
    compile("io.ktor:ktor-server-netty:$ktorVersion")
    compile("org.apache.kafka:kafka-clients:2.1.0")

    compile("org.apache.logging.log4j:log4j-core:jar:2.11.1")
    compile("org.slf4j:slf4j-api:1.7.5")
    compile("org.slf4j:slf4j-log4j12:1.7.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}