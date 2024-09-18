@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "com.noxis.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get()))
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "noxis.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidApplication") {
            id = "noxis.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidCompose") {
            id = "noxis.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "app.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}