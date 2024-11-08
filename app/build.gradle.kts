plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
   // id("kotlin-kapt")
}

android {
    namespace = "com.example.employeedir"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.employeedir"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //kapt(libs.androidx.room.compiler)
    implementation(libs.squareup.retrofit2.retrofit)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.bumptech.glide)
    implementation(libs.androidx.recyclerview)
    implementation(libs.coil.compose)
    implementation("com.google.accompanist:accompanist-swiperefresh:0.28.0")
    implementation("androidx.compose.foundation:foundation:1.5.3")
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.foundation:foundation:1.6.0-alpha07")
    implementation ("androidx.compose.material3:material3:1.2.0")
    // Unit Testing
    testImplementation("junit:junit:4.13.2")

// For mocking
    testImplementation ("org.mockito:mockito-core:3.9.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:3.2.0")

// For coroutines testing
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")
    // Add mockito-inline to allow mocking final classes
    testImplementation ("org.mockito:mockito-inline:3.12.4")







//    kapt("androidx.room:room-compiler:2.5.0")
//        ksp(libs.androidx.room.compiler.v250)
}