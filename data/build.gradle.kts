plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.serialization.ktx)
    alias(libs.plugins.room.plugin)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.spotless)
}

room {
    schemaDirectory("$projectDir/schemas/")
}

android {
    namespace = "io.github.livenlearnaday.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        /* Retrieves API from local.properties */
        val properties = org.jetbrains.kotlin.konan.properties.Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "BASE_COUNTRY_LIST_ENDPOINT", "\"https://livenlearnaday.github.io/data/countries/\"")
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    val properties = org.jetbrains.kotlin.konan.properties.Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())

        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvm.get()
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":domain"))

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.timber)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.koin)
    implementation(libs.coroutines)

    // db
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // test
    testImplementation(libs.bundles.test.impl)
}
