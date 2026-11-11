import com.alfonsonavio.nexbank.buildlogic.configureCompose
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Adds Compose to an Android module. Apply it together with
 * nexbank.android.application or nexbank.android.library, in any order.
 */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            pluginManager.withPlugin("com.android.application") {
                configureCompose(extensions.getByType<ApplicationExtension>())
            }
            pluginManager.withPlugin("com.android.library") {
                configureCompose(extensions.getByType<LibraryExtension>())
            }
        }
    }
}