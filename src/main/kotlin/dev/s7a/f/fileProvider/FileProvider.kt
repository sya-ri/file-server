package dev.s7a.f.fileProvider

import dev.s7a.f.logger
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondFile
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.io.File

/**
 * Get files
 */
sealed interface FileProvider {
    /**
     * Name
     */
    val name: String

    /**
     * Current settings
     */
    val settings: List<String>

    /**
     * Get file
     */
    suspend fun get(path: String): File?

    /**
     * Print fileProvider settings
     */
    fun printSettings() {
        logger.info("Using FileProvider is $name")
        settings.forEach { logger.info("- $it") }
    }

    /**
     * Setup routing
     */
    fun setupRoute(application: Application) {
        application.routing {
            get("{path...}") {
                val path = call.parameters.getAll("path") ?: return@get
                val file = get(path.joinToString("/")) ?: return@get
                call.respondFile(file)
            }
        }
    }

    companion object {
        /**
         * Get [FileProvider] by name
         */
        fun getByName(name: String) = setOf(
            LocalFileProvider,
            WebDAVFileProvider,
        ).firstOrNull {
            it.name.equals(name, true)
        }
    }
}
