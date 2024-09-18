import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            requireNotNull(applicationExtension).apply {
                buildFeatures {
                    buildConfig = true
                }

                configureKotlin()
                configureKotlinAndroid(this)
            }

//            extensions.configure<ApplicationExtension> {
//                configureKotlinAndroid(this)
//                configureKotlin()
//                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
//            }
        }
    }

}