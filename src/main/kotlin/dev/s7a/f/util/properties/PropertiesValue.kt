package dev.s7a.f.util.properties

import java.util.Properties
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get value from [Properties]
 *
 * @see PropertiesFile.keyValue
 */
class PropertiesValue(private val properties: Properties, private val key: String) : ReadWriteProperty<Any?, String?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return properties.getProperty(key)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        if (value.isNullOrEmpty()) {
            properties.remove(key)
        } else {
            properties.setProperty(key, value)
        }
    }
}
