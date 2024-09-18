plugins {
    `kotlin-dsl`
}

group = "com.noxis.gradleplugins"


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}