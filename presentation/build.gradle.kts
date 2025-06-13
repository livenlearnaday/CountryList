plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.spotless)
}

android {
    namespace = "io.github.livenlearnaday.presentation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvm.get()
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.bundles.koin)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.bundles.navigation)

    implementation(libs.timber)
    implementation(libs.bundles.coil)
    implementation(libs.androidx.animation.graphics.android)
    implementation(libs.androidx.constraintlayout.compose)

    // test
    testImplementation(libs.bundles.test.impl)
    androidTestImplementation(libs.bundles.android.test.impl)
    androidTestImplementation(libs.bundles.navigation)
    debugImplementation(libs.bundles.debug.impl)
    androidTestImplementation(libs.ktorClientCio)
}
