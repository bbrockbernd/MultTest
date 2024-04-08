plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlinx:lincheck:2.28")
    implementation("org.multiverse:multiverse-core:0.7.0")
    testImplementation("junit:junit:4.13")
    implementation("com.google.guava:guava:30.1-jre")
}

kotlin {
    jvmToolchain(19)
}

tasks.withType<Test> { 
    jvmArgs( "--add-opens", "java.base/java.lang=ALL-UNNAMED", "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED", "--add-exports", "java.base/jdk.internal.util=ALL-UNNAMED", "--add-exports", "java.base/sun.security.action=ALL-UNNAMED" ) 
    maxHeapSize = "16g"
}