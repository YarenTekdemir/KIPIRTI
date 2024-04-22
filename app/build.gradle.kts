plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.app"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        compose = true
    }

    defaultConfig {
        applicationId = "com.example.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.1.0-alpha04")
    implementation("androidx.compose.ui:ui-graphics:1.1.0-alpha04")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.0-alpha04")
    implementation("androidx.compose.material3:material3:1.0.0-alpha08")
    implementation("androidx.appcompat:appcompat:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.0-alpha04")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.0-alpha04")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.0-alpha04")
    implementation("com.google.android.material:material:1.4.0")
    implementation("com.github.jinatonic.confetti:confetti:1.1.2")
    implementation("nl.dionsegijn:konfetti-compose:2.0.4")
    implementation("nl.dionsegijn:konfetti-xml:2.0.4")
}
