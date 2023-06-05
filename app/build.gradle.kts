import com.cryptoCurrencyApp.Configuration
import com.cryptoCurrencyApp.Versions

plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
    kotlin("android")
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = Configuration.AndroidJUnitRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    namespace = "com.example.cryptocurrencyapp"
}

dependencies {
    implementation("androidx.core:core-ktx:${Versions.CORE}")
    implementation("androidx.appcompat:appcompat:${Versions.APPCOMPAT}")
    implementation("com.google.android.material:material:${Versions.MATERIAL}")
    implementation("androidx.mediarouter:mediarouter:${Versions.MEDIAROUTER}")
    implementation("androidx.paging:paging-runtime-ktx:${Versions.PAGING}")

    //livecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}")

    //Coil and Lottie
    implementation("io.coil-kt:coil-compose:${Versions.COIL}")
    implementation("com.airbnb.android:lottie:${Versions.LOTTIE}")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}")
    implementation("com.google.code.gson:gson:${Versions.GSON}")

    //Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Room
    implementation("androidx.room:room-runtime:${Versions.ROOM}")
    implementation("androidx.room:room-ktx:${Versions.ROOM}")
    kapt("androidx.room:room-compiler:${Versions.ROOM}")

    //Compose
    implementation("androidx.compose.material:material:${Versions.COMPOSE_UI_TOOLING}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_UI_TOOLING}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.COMPOSE_UI_TOOLING}")
    implementation("androidx.compose.ui:ui:${Versions.COMPOSE_UI}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_UI}")
    implementation("androidx.compose.compiler:compiler:1.4.3")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.foundation:foundation:1.3.1")

    //Koin
    implementation("io.insert-koin:koin-android:${Versions.KOIN}")
    implementation("io.insert-koin:koin-androidx-compose:${Versions.KOIN}")

    //modules
    implementation(project(":repository"))
    implementation(project(":abstraction"))
}
