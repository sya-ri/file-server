package dev.s7a.f.fileProvider

import dev.s7a.f.Config
import dev.s7a.f.logger
import dev.s7a.f.util.Statuses
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.isSuccess
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.io.path.createTempFile
import kotlin.system.exitProcess

/**
 * Use remote file via WebDAV as [FileProvider]
 */
object WebDAVFileProvider : FileProvider {
    private val url = Config.FileProvider.WebDAV.url
    private val userName = Config.FileProvider.WebDAV.userName

    init {
        if (url.isEmpty() || userName.isEmpty()) {
            logger.error("WebDAV url or userName is empty. Please check config.properties.")
            exitProcess(Statuses.IllegalSetting.exitCode)
        }
    }

    /**
     * WebDAV client
     */
    private val client = HttpClient(CIO) {
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(userName, Config.FileProvider.WebDAV.password)
                }
            }
        }
        expectSuccess = false
    }

    /**
     * WebDAV logger
     */
    private val clientLogger: Logger = LoggerFactory.getLogger("WebDAV")

    override val name = "WebDAV"
    override val settings = buildList {
        add("url: $url")
        add("userName: $userName")
        add("password: *****")
    }

    override suspend fun get(path: String): File? {
        val suffix = path.substringAfterLast('.', "").let { extension ->
            if (extension.isEmpty()) "" else ".$extension"
        }
        val response = client.get("$url/$path")
        return if (response.status.isSuccess()) {
            createTempFile(suffix = suffix).toFile().apply {
                response.bodyAsChannel().copyAndClose(writeChannel())
                clientLogger.info("${response.status}: $path (${length()})")
            }
        } else {
            null
        }
    }
}
