plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.arthenica:smart-exception-java9:0.2.1")
    implementation("com.arthenica:smart-exception-common:0.2.1")
}