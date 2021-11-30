package dev.s7a.f.fileProvider

import dev.s7a.f.Config

/**
 * Use local files as [FileProvider]
 */
object LocalFileProvider : FileProvider {
    override val name = "Local"

    override val settings
        get() = buildList {
            add("root: ${Config.FileProvider.Local.root}")
        }
}
