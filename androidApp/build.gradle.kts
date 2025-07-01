plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp") version "2.0.0-1.0.24"
    //Kotlinx Serialization
    kotlin("plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.cody.haievents.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.cody.haievents.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
//    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3.android)
    debugImplementation(libs.compose.ui.tooling)

    implementation ("androidx.compose.material:material-icons-extended:1.7.8")


    implementation ("androidx.navigation:navigation-compose:2.8.9")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("io.github.raamcosta.compose-destinations:core:1.8.38-beta")

    ksp("io.github.raamcosta.compose-destinations:ksp:1.8.38-beta")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}