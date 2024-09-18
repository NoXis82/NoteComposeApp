import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidHilt(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    if (commonExtension is ApplicationExtension) {
        pluginManager.apply(libs.findPlugin("hilt-android").get().get().pluginId)
    }

    pluginManager.apply("com.google.devtools.ksp")
    pluginManager.apply("org.jetbrains.kotlin.kapt")

    dependencies {
//        add("implementation", libs.findLibrary("hilt-android").get())
//        add("kapt", libs.findLibrary("hilt-android-compiler").get())
//        add("ksp", libs.findLibrary("hilt-android-compiler").get())
    }
}