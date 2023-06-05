import com.cryptoCurrencyApp.Configuration
import com.cryptoCurrencyApp.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.apilibrary"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        testInstrumentationRunner = Configuration.AndroidJUnitRunner
        consumerProguardFiles("consumer-rules.pro")
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

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${Versions.CORE_KTX}")
    implementation("androidx.appcompat:appcompat:${Versions.APPCOMPAT}")
    implementation("com.google.android.material:material:${Versions.MATERIAL}")
    testImplementation("junit:junit:${Versions.JUNIT}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}")

    // Glide
    implementation("com.github.bumptech.glide:glide:${Versions.GLIDE}")

    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.GSON}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}")
    implementation("com.google.code.gson:gson:${Versions.GSON}")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES_ANDROID}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_COROUTINES_TEST}")

    // Http
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR}")

    // Mockk
    testImplementation("io.mockk:mockk:${Versions.MOCKK}")

    // Room components
    implementation("androidx.room:room-runtime:${Versions.ROOM}")
    kapt("androidx.room:room-compiler:${Versions.ROOM}")
    implementation("androidx.room:room-ktx:${Versions.ROOM}")
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.RUNTIME_LIVEDATA}")

    implementation(project(":abstraction"))
}