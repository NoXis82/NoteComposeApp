import com.android.build.api.dsl.ApplicationExtension
import com.noxis.gradleplugins.configureKotlinAndroid
import com.noxis.gradleplugins.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(project.pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply(libs.findPlugin("kotlin.serialization").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureDefaultConfig()
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())

            }
        }
    }
}

private fun ApplicationExtension.configureDefaultConfig() {
    defaultConfig {
        applicationId = "com.noxis.notecomposeapp"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}