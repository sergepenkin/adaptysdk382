
plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(platform("io.adapty:adapty-bom:3.8.2"))
    implementation("io.adapty:android-sdk")
    implementation("io.adapty:android-ui")
    implementation(libs.kotlin.stdlib.jdk7)
}
