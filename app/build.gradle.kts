import com.cryptoCurrencyApp.Configuration

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

val groupVersions = mapOf(
        "lifecycle_version" to "2.6.0",
        "core_version" to "1.9.0",
        "appcompat_version" to "1.6.1",
        "material_version" to "1.8.0",
        "mediarouter_version" to "1.3.1",
        "paging_version" to "3.1.1",
        "coil_version" to "2.2.2",
        "lottie_version" to "3.4.0",
        "retrofit_version" to "2.9.0",
        "gson_version" to "2.8.9",
        "room_version" to "2.5.0",
        "compose_ui_version" to "1.3.3",
        "compose_ui_tooling" to "1.4.0-rc01"
)

dependencies {
    implementation("androidx.core:core-ktx:${groupVersions["core_version"]}")
    implementation("androidx.appcompat:appcompat:${groupVersions["appcompat_version"]}")
    implementation("com.google.android.material:material:${groupVersions["material_version"]}")
    implementation("androidx.mediarouter:mediarouter:${groupVersions["mediarouter_version"]}")
    implementation("androidx.paging:paging-runtime-ktx:${groupVersions["paging_version"]}")

    //livecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${groupVersions["lifecycle_version"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${groupVersions["lifecycle_version"]}")

    //Coil and Lottie
    implementation("io.coil-kt:coil-compose:${groupVersions["coil_version"]}")
    implementation("com.airbnb.android:lottie:${groupVersions["lottie_version"]}")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${groupVersions["retrofit_version"]}")
    implementation("com.squareup.retrofit2:converter-gson:${groupVersions["retrofit_version"]}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${groupVersions["retrofit_version"]}")
    implementation("com.google.code.gson:gson:${groupVersions["gson_version"]}")

    //Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Room
    implementation("androidx.room:room-runtime:${groupVersions["room_version"]}")
    implementation("androidx.room:room-ktx:${groupVersions["room_version"]}")
    kapt("androidx.room:room-compiler:${groupVersions["room_version"]}")

    //Compose
    implementation("androidx.compose.material:material:${groupVersions["compose_ui_tooling"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${groupVersions["compose_ui_tooling"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${groupVersions["compose_ui_tooling"]}")
    implementation("androidx.compose.ui:ui:${groupVersions["compose_ui_version"]}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${groupVersions["compose_ui_version"]}")
    implementation("androidx.compose.compiler:compiler:1.4.3")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.foundation:foundation:1.3.1")

    //modules
    implementation(project(":repository"))
    implementation(project(":abstraction"))
}