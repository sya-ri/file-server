package dev.s7a.f

import dev.s7a.f.util.properties.PropertiesFile

/**
 * File server settings
 */
object Config : PropertiesFile("config.properties") {
    /**
     * Web server port
     */
    val port by keyValue("Port").int(8080)
}
