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
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

val catalogOrg = requiredGradleProperty("catalogOrg")
val catalogGroup = requiredGradleProperty("catalogGroup")
val catalogRepo = requiredGradleProperty("catalogRepo")
val catalogName = requiredGradleProperty("catalogName")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://$catalogOrg.github.io/$catalogRepo/")
        }
    }
    versionCatalogs {
        create("libs") {
            from("$catalogGroup:$catalogName")
        }
    }
}

rootProject.name = requiredGradleProperty("rootProjectName")

include(":core", ":javafx", ":app")

/**
 * Retrieves the value of the Gradle property specified by [name], throwing an exception if it's not
 * available.
 *
 * @param name
 * @return the value of the [name] property.
 * @throws IllegalStateException if no value has been assigned to the [name] property.
 */
fun requiredGradleProperty(name: String): String =
    providers.gradleProperty(name).get()
