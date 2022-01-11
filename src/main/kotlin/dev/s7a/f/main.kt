@file:JvmName("Main")

package dev.s7a.f

import dev.s7a.f.fileProvider.FlexibleFileProvider.Companion.asFlexible
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.plugins.AutoHeadResponse
import io.ktor.server.plugins.CallLogging
import io.ktor.server.plugins.Compression
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * General logger
 */
val logger: Logger = LoggerFactory.getLogger("FileServer")

fun main() {
    val fileProvider = Config.fileProvider.asFlexible(Config.enableFlexible)
    fileProvider.printSettings()
    embeddedServer(CIO, port = Config.port) {
        install(CallLogging)
        install(AutoHeadResponse)
        install(Compression)
        fileProvider.setupRoute(this)
    }.start(wait = true)
}
