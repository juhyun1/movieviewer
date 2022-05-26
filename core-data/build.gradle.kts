import com.themovieviewer.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("androidx.room:room-runtime:${Versions.ROOM}")
    implementation("androidx.room:room-ktx:${Versions.ROOM}")
    implementation("androidx.room:room-paging:${Versions.ROOM}")
    kapt("androidx.room:room-compiler:${Versions.ROOM}")
    androidTestImplementation("androidx.room:room-testing:${Versions.ROOM}")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")// For Kotlin use paging-runtime-ktx

    implementation("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}")
    // Retrofit Coroutines Support
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.RETROFIT_COROUTINES}")
    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}")

    // Dagger Core
//    implementation("com.google.dagger:dagger:${Versions.HILT}")
//    kapt("com.google.dagger:dagger-compiler:${Versions.HILT}")

    // Dagger Android
//    api("com.google.dagger:dagger-android:${Versions.HILT}")
//    api("com.google.dagger:dagger-android-support:${Versions.HILT}")
//    kapt("com.google.dagger:dagger-android-processor:${Versions.HILT}")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE_VIEW_MODEL}")
    kapt("androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER}")

    // Hilt testing dependencies
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.HILT}")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:${Versions.HILT}")

    implementation(project(":model"))

}