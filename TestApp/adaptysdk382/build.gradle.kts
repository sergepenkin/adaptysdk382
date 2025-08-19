plugins {
    //id("java-library")
    id("com.android.library")
    //id("org.jetbrains.kotlin.android")
    kotlin("android")

    //alias(libs.plugins.jetbrains.kotlin.jvm)
    //kotlin("jvm") version "2.0.21" // or your Kotlin version
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
// Add this to handle the platform mismatch
configurations.all {
    attributes {
        attribute(
            org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.attribute,
            org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.androidJvm
        )
    }
}

// Optional: Configure test tasks
tasks.register("testClasses") {
    //dependsOn("testDebugUnitTestClasses")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile =
                file("D:\\Projects\\Misradit\\CompanyBooksProMobile\\CompanyBooksProMobile.Android\\Keystore\\misradit.keystore")
            storePassword = "1Q2w3e4r"
            keyPassword = "1Q2w3e4r"
            keyAlias = "misradit"
        }
    }
    namespace = "com.yourpackage.adaptysdk382"  // Replace with your package name

    compileSdk = 35  // Or your target SDK version

    defaultConfig {
        minSdk = 21  // Adapty's minimum SDK version

        // If this is an application module, you'd also need:
        // applicationId = "com.yourpackage.adaptysdk382"
         targetSdk = 35
        // versionCode = 1
        // versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            //proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }

}
dependencies {
    implementation(platform(libs.adapty.adapty.bom))
    implementation(libs.adapty.android.sdk)
    implementation(libs.adapty.android.ui)
    implementation(libs.internal.crossplatform)
}

