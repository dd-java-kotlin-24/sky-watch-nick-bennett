/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.ksp)
    alias(libs.plugins.schema.parser)
    alias(libs.plugins.junit)
}

val subprojectProperties =
    providers.fileContents(layout.projectDirectory.file("gradle.properties"))
        .asText
        .map { contents ->
            Properties().apply {
                contents.reader().use { load(it) }
            }
        }

val configuredBasePackage = subprojectProperty("basePackageName")
val configuredTargetSdk = subprojectProperty("targetSdk").map(String::toInt)
val configuredMinSdk = subprojectProperty("minSdk").map(String::toInt)
val configuredVersionCode = subprojectProperty("versionCode").map(String::toInt)
val configuredVersionName = subprojectProperty("version")
val configuredAppName = subprojectProperty("appName")

android {

    namespace = configuredBasePackage.get()
    compileSdk = configuredTargetSdk.get()

    defaultConfig {

        applicationId = configuredBasePackage.get()
        minSdk = configuredMinSdk.get()
        targetSdk = configuredTargetSdk.get()
        versionCode = configuredVersionCode.get()
        versionName = configuredVersionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] =
            "de.mannodermaus.junit5.AndroidJUnit5Builder"

        resValue("string", "app_name", configuredAppName.get())

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.valueOf("VERSION_${libs.versions.java.get()}")
        targetCompatibility = JavaVersion.valueOf("VERSION_${libs.versions.java.get()}")
    }

    buildFeatures {
        resValues = true
        viewBinding = true
        // Enable dataBinding if desired.
        // dataBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/NOTICE.md"
            excludes += "/META-INF/LICENSE.md"
        }
    }

}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.valueOf("JVM_${libs.versions.java.get()}")
    }
}

ksp {
    mapOf(
        "room.schemaLocation" to "$projectDir/schemas",
        "room.incremental" to "true",
        "room.expandProjection" to "true"
    ).forEach { (key, value) ->
        arg(key, value)
    }
}

dependencies {

    implementation(project(":core"))

    // Kotlin core & async libraries
    implementation(libs.kotlin)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.jdk8)
    implementation(libs.kotlin.coroutines.android)

    // Basic Android components
    implementation(libs.app.compat)
    implementation(libs.activity)
    implementation(libs.fragment)

    // Common additional widgets
    implementation(libs.constraint.layout)
    implementation(libs.recycler.view)

    // Navigation framework libraries
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Lifecycle (LiveData & ViewModel) libraries
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Preferences/settings components
    implementation(libs.preference)

    // Material Design components & styles
    implementation(libs.material)

    // Room ORM library & annotation processor
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // Hilt dependency-injection library & annotation processor
    implementation(libs.hilt.android.core)
    implementation(libs.dagger.core)
    ksp(libs.hilt.compiler)

    // Gson (Google JSON parser) library
    // implementation(libs.gson)

    // Moshi (Square JSON parser) library
    // implementation(libs.moshi.core)
    // implementation(libs.moshi.kotlin)
    // implementation(libs.moshi.kotlin.codegen)
    // implementation(libs.moshi.adapters)

    // Android/Google authentication libraries
    // implementation(libs.play.auth) // Legacy
    // implementation(libs.credentials)
    // implementation(libs.credentials.play.services)
    // implementation(libs.googleid)

    // Retrofit (REST client) with Gson & RxJava integration
    // implementation(libs.retrofit.core)
    // implementation(libs.retrofit.converter.gson)
    // implementation(libs.retrofit.converter.moshi)
    // implementation(libs.retrofit.adapter.rx.java)
    // implementation(libs.retrofit.adapter.java8)

    // OkHttp core & logging libraries
    // implementation(libs.okio)
    // implementation(libs.okhttp)
    // implementation(libs.logging.interceptor)

    // Libraries for JVM-based testing.
    testImplementation(libs.junit.api)
    testImplementation(libs.junit.params)
    testRuntimeOnly(libs.junit.engine)

    // Libraries for instrumented (run in Android) testing.
    androidTestImplementation(libs.android.test.runner)
    androidTestImplementation(libs.junit.android.core)
    androidTestRuntimeOnly(libs.junit.android.runner)
    androidTestImplementation(libs.junit.api)
    androidTestImplementation(libs.junit.params)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestAnnotationProcessor(libs.hilt.compiler)
    androidTestAnnotationProcessor(libs.hilt.android.compiler)

    constraints {
        implementation(libs.kotlin.jdk7) {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        //noinspection ForeignDelegate
        implementation(libs.kotlin.jdk8) {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
}

roomDdl {
    // TODO Change source location to match local database.
    source.set(project.file("$projectDir/schemas/edu.cnm.deepdive.myproject.service.LocalDatabase/1.json"))
    destination.set(project.file("$projectDir/../docs/sql/ddl.sql"))
}

fun subprojectProperty(name: String): Provider<String> =
    subprojectProperties.map { properties ->
        properties.getProperty(name)
            ?: throw GradleException(
                "Property '$name' is missing from subproject gradle.properties"
            )
    }

fun getLocalProperty(name: String): String {
    return getProperty("$projectDir/local.properties", name)
}

fun getProperty(filename: String, name: String): String {
    return FileInputStream(filename).use {
        val props = Properties()
        props.load(it)
        props.getProperty(name)
    }
}
