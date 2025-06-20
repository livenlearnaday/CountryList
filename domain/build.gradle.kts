plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.spotless)
}

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.coroutines)

    // test
    testImplementation(libs.bundles.test.impl)
}
