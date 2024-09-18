plugins {
    `kotlin-dsl`
}

group = "com.noxis.gradleplugins"


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationComposeConventionPlugin") {
            id = "gradlePlugins.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplicationConventionPlugin") {
            id = "gradlePlugins.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidHiltConventionPlugin") {
            id = "gradlePlugins.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}