package dev.s7a.f.fileProvider

import dev.s7a.f.Config
import java.io.File

/**
 * Use local files as [FileProvider]
 */
object LocalFileProvider : FileProvider {
    private val root = Config.FileProvider.Local.root

    override val name = "Local"
    override val settings = buildList {
        add("root: ${File(root).toRelativeString(File("."))}/")
    }

    override suspend fun get(path: String): File? {
        return File("$root/$path").takeIf(File::exists)?.takeIf(File::isFile)
    }
}
