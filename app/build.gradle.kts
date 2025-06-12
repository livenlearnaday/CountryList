plugins {
    alias(libs.plugins.application.android)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.serialization.ktx)
    alias(libs.plugins.spotless)
}

android {
    namespace = "io.github.livenlearnaday.countrylist"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    /* Retrieves API from local.properties */
    val properties = org.jetbrains.kotlin.konan.properties.Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "io.github.livenlearnaday.countrylist"
        minSdk = libs.versions.android.minSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true

        multiDexEnabled = true

        buildConfigField("String", "WEATHERSTACK_KEY", "\"${properties["CLIENT_KEY"]}\"")
        buildConfigField("String", "WEATHERSTACK_ENDPOINT", "\"https://api.weatherstack.com/\"")
        buildConfigField("String", "BASE_URL", "\"https://livenlearnaday.github.io/data/countries/\"")


    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())

        isCoreLibraryDesugaringEnabled = true
    }


    kotlinOptions {
        jvmTarget = libs.versions.jvm.get()
        allWarningsAsErrors = false
        freeCompilerArgs = listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }

    signingConfigs {
        create("release") {
            this.keyAlias = "${properties["KEY_ALIAS"]}"
            this.keyPassword = "${properties["KEY_PASSWORD"]}"
            this.storeFile = file("${properties["KEY_FILE_PATH"]}")
            this.storePassword = "${properties["STORE_PASSWORD"]}"
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isDebuggable = false
            aaptOptions.cruncherEnabled = false
            multiDexEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "../proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/licenses/ASM"
            )
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)


    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // navigation
    implementation(libs.bundles.navigation)

    //Timber logging
    implementation(libs.timber)

    // coroutines
    implementation(libs.coroutines)

    // koin di
    implementation(libs.bundles.koin)
    implementation(libs.bundles.koin.compose)


    implementation(libs.bundles.coil)



}