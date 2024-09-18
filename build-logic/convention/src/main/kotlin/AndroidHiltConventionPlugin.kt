import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            commonExtension.apply {
                configureAndroidHilt(this)
            }
        }
    }

}