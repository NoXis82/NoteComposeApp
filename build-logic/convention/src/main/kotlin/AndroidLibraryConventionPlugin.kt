import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin


class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
           pluginManager.run {
               apply("com.android.library")
               apply("org.jetbrains.kotlin.android")

           }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureKotlin()
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
            }

            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }
        }
    }
}
