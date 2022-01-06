package dev.s7a.f.fileProvider

import dev.s7a.f.logger
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
