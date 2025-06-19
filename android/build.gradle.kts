import java.util.Properties

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.arthenica.ffmpegkit"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")

        externalNativeBuild {
            cmake {
                cppFlags.add("")
            }
        }

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
            java.srcDirs("src/main/java")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
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
//val flutterSdkPath = gradle.extra.get("flutterSdkPath")
//val flutterSdkPath = run {
//    val properties = Properties()
//    file("local.properties").inputStream().use { properties.load(it) }
//    val flutterSdkPath = properties.getProperty("flutter.sdk")
//    require(flutterSdkPath != null) { "flutter.sdk not set in local.properties" }
//    flutterSdkPath
//}
//println("这里获取到的 Flutter SDK Path: $flutterSdkPath")
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.arthenica:smart-exception-java9:0.2.1")
    implementation("com.arthenica:smart-exception-common:0.2.1")
//    compileOnly(files("$flutterSdkPath/bin/cache/artifacts/engine/android-arm/flutter.jar"))
}

//subprojects {
//    project.evaluationDependsOn(":app")
//}

//tasks.register<Delete>("clean") {
//    delete(rootProject.layout.buildDirectory)
//}
