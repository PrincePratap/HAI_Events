import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.0.0"

}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    //dependencies versions
    val coroutinesVersion = "1.6.4"
    val koinVersion = "3.3.2"
    val ktorVersion = "2.2.1"
    val datastoreVersion = "1.1.1"

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            api("io.insert-koin:koin-core:$koinVersion")

            implementation("androidx.datastore:datastore-preferences-core:$datastoreVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            api("io.insert-koin:koin-android:$koinVersion")
            implementation("io.ktor:ktor-client-android:$ktorVersion")
            api("androidx.datastore:datastore-preferences:$datastoreVersion")
//            implementation("com.razorpay:checkout:1.6.40")

        }

    }
}

android {
    namespace = "com.cody.haievents"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(libs.androidx.runtime.android)
    implementation(libs.checkout)
    implementation(libs.androidx.foundation.layout.android)
}
