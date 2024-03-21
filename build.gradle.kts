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

}

//tasks.test {
//    useJUnitPlatform()
//}
kotlin {
    jvmToolchain(19)
}