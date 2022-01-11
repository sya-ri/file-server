package dev.s7a.f.fileProvider

import java.io.File

/**
 * If a local file exists, respond with that file. Otherwise, use [fileProvider].
 */
class FlexibleFileProvider(private val fileProvider: FileProvider) : FileProvider {
    companion object {
        /**
         * If [enable] is true, returned as [FlexibleFileProvider]
         */
        fun FileProvider.asFlexible(enable: Boolean): FileProvider {
            return if (enable && this !is LocalFileProvider) {
                FlexibleFileProvider(this)
            } else {
                this
            }
        }
    }

    init {
        require(fileProvider !is LocalFileProvider) { "fileProvider must not be LocalFileProvider" }
    }

    override val name = "Flexible(${fileProvider.name})"
    override val settings = LocalFileProvider.settings + fileProvider.settings

    override suspend fun get(path: String): File? {
        return LocalFileProvider.get(path) ?: fileProvider.get(path)
    }
}
