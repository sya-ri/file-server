@file:JvmName("Main")

package dev.s7a.f

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
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
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }.start(wait = true)
}
