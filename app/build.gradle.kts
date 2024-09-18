plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.jetbrains.kotlin.android)

    // other plugins
    alias(libs.plugins.common.android.application)
    alias(libs.plugins.common.android.application.compose)
    alias(libs.plugins.common.android.hilt)
    alias(libs.plugins.common.android.room)

//    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.kapt)
}

android {
    namespace = "com.noxis.notecomposeapp"
//    compileSdk = 34

//    defaultConfig {
//        applicationId = "com.noxis.notecomposeapp"
//        minSdk = 26
//        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "com.noxis.notecomposeapp.HiltTestRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)

    //Hilt
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
//    implementation(libs.hilt.navigation.compose)

    //Navigation
    implementation(libs.navigation.compose)

    //Room
//    implementation(libs.room.runtime)
//    implementation(libs.room.ktx)
//    kapt(libs.room.compiler)

    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.truth)

    //mockk
    testImplementation(libs.mockk)
    //mockito
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.core)
    //
    testImplementation(libs.kotlinx.coroutines.test)
//    implementation(libs.androidlibrarytest)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}