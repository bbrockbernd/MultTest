plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Libraries under test
    implementation("org.multiverse:multiverse-core:0.7.0")
    implementation("com.google.guava:guava:33.1.0-jre")
    implementation("org.agrona:agrona:1.21.1")
    implementation("it.unimi.dsi:fastutil:8.5.13")
    
    // Testing utilities
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlinx:lincheck:2.28")
    testImplementation("junit:junit:4.13")
}

kotlin {
    jvmToolchain(19)
}

tasks.withType<Test> { 
    jvmArgs( "--add-opens", "java.base/java.lang=ALL-UNNAMED", "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED", "--add-exports", "java.base/jdk.internal.util=ALL-UNNAMED", "--add-exports", "java.base/sun.security.action=ALL-UNNAMED" ) 
    maxHeapSize = "16g"
}