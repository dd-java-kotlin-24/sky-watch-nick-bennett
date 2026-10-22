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
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.util.*

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.openjfx)
    application
    jacoco
}

val javaVersion = libs.versions.java.get()
val subprojectProperties =
    providers.fileContents(layout.projectDirectory.file("gradle.properties"))
        .asText
        .map { contents ->
            Properties().apply {
                contents.reader().use { load(it) }
            }
        }

kotlin {
    jvmToolchain(javaVersion.toInt())
}

javafx {
    version = libs.versions.openjfx.library.get()
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass = subprojectProperty("mainClass")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

dependencies {

    implementation(project(":core"))

    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit.aggregator)
    testRuntimeOnly(libs.junit.engine)
    testRuntimeOnly(libs.junit.platform)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events.addAll(setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED))
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.javadoc {
    with(options as StandardJavadocDocletOptions) {
        links("https://docs.oracle.com/en/java/javase/${javaVersion}/docs/api/")
    }
}

fun subprojectProperty(name: String): Provider<String> =
    subprojectProperties.map { properties ->
        properties.getProperty(name)
            ?: throw GradleException(
                "Property '$name' is missing from subproject gradle.properties"
            )
    }
