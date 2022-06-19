import com.themovieviewer.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha08"
    }
}

dependencies {
    implementation(files("libs/YouTubeAndroidPlayerApi.jar"))

    implementation("androidx.appcompat:appcompat:${Versions.APPCOMPAT}")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}")
    implementation("androidx.core:core-ktx:${Versions.CORE_KTX}")
    implementation("androidx.paging:paging-runtime-ktx:${Versions.PAGING}")

    //LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}")

    //NAVIGATION
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_KTX}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_KTX}")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // Compose
    implementation("androidx.compose.runtime:runtime:${Versions.COMPOSE}")
    implementation("androidx.compose.foundation:foundation-layout:${Versions.COMPOSE}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}")
    implementation("androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}")
    implementation("androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}")

    implementation("androidx.navigation:navigation-compose:${Versions.NAVIGATION_COMPOSE}")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.VIEWMODEL_COMPOSE}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE}")

    //material
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.compose.material:material:${Versions.COMPOSE}")
    implementation("androidx.compose.material:material-icons-extended:${Versions.COMPOSE}")
    implementation("androidx.compose.material3:material3:${Versions.MATERIAL3}")
    implementation("androidx.compose.material3:material3-window-size-class:${Versions.MATERIAL3}")

    implementation("androidx.paging:paging-compose:${Versions.PAGING_COMPOSE}")

    //TIMBER
    implementation("com.jakewharton.timber:timber:${Versions.TIMBER}")

    //Coil
    implementation("io.coil-kt:coil-compose:${Versions.COIL_COMPOSE}")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:${Versions.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.HILT}")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE_VIEW_MODEL}")
    kapt("androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER}")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.4.2")

    // Hilt testing dependencies
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.HILT}")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:${Versions.HILT}")

    implementation(project(":core-model"))
    implementation(project(":core-ui"))
    implementation(project(":core-data"))
    implementation(project(":core-common"))
    implementation(project(":feature-people"))

}
