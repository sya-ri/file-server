package dev.s7a.f

import dev.s7a.f.util.properties.PropertiesFile
import dev.s7a.f.util.properties.types.default
import dev.s7a.f.util.properties.types.fileProvider
import dev.s7a.f.util.properties.types.int

/**
 * File server settings
 */
object Config : PropertiesFile("config.properties") {
    /**
     * Web server port
     */
    val port by keyValue("Port").int(8080)

    /**
     * Get files
     */
    val fileProvider by keyValue("FileProvider").fileProvider()

    /**
     * File provider settings
     */
    object FileProvider {
        /**
         * @see dev.s7a.f.fileProvider.LocalFileProvider
         */
        object Local {
            /**
             * Root folder
             */
            val root by keyValue("FileProvider_Local_Root").default("./")
        }
    }
}
