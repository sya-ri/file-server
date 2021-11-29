package dev.s7a.f.util.properties

import dev.s7a.f.util.copyFromResource
import java.io.File
import java.util.Properties

/**
 * @see Properties
 */
open class PropertiesFile(fileName: String) {
    private val file = File(fileName)
    private var properties: Properties

    init {
        if (file.exists().not()) {
            file.createNewFile()
            file.copyFromResource(fileName)
        }
        properties = Properties().apply { load(file.inputStream()) }
    }

    /**
     * Definition of value using delegation
     *
     * @param key value key
     */
    fun keyValue(key: String) = PropertiesValue(properties, key)
}
