package com.noxis.gradleplugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidRoom(
//    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply(libs.findPlugin("kapt").get().get().pluginId)

    dependencies {
        add("implementation", libs.findLibrary("room.runtime").get())
        add("implementation", libs.findLibrary("room.ktx").get())
        add("kapt", libs.findLibrary("room.compiler").get())
    }
}