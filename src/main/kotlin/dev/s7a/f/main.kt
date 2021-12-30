@file:JvmName("Main")

package dev.s7a.f

import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.CallLogging
import io.ktor.server.response.respondFile
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * General logger
 */
val logger: Logger = LoggerFactory.getLogger("FileServer")

fun main() {
    val fileProvider = Config.fileProvider
    logger.info("Using FileProvider is ${fileProvider.name}")
    fileProvider.settings.forEach { logger.info("- $it") }
    embeddedServer(CIO, port = Config.port) {
        install(CallLogging)
        routing {
            get("{path...}") {
                val path = call.parameters.getAll("path") ?: return@get
                val file = fileProvider.get(path.joinToString("/")) ?: return@get
                call.respondFile(file)
            }
        }
    }.start(wait = true)
}
