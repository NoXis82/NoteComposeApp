import com.android.build.api.dsl.ApplicationExtension
import com.noxis.gradleplugins.configureAndroidRoom
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureAndroidRoom()
            }
        }
    }
}