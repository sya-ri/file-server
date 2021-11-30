package dev.s7a.f.fileProvider

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
