import com.themovieviewer.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.6.21"
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 27
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${Versions.CORE_KTX}")
    implementation("androidx.appcompat:appcompat:${Versions.APPCOMPAT}")
    implementation("com.google.android.material:material:${Versions.MATERIAL}")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE_VIEW_MODEL}")
    kapt("androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER}")


    // Hilt testing dependencies
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.HILT}")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:${Versions.HILT}")

    //TIMBER
    implementation("com.jakewharton.timber:timber:${Versions.TIMBER}")

    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.4.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}
