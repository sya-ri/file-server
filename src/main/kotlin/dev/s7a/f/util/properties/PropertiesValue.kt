package dev.s7a.f.util.properties

import java.util.Properties
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get value from [Properties]
 *
 * @see PropertiesFile.keyValue
 */
class PropertiesValue(private val properties: Properties, private val key: String): ReadWriteProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return properties.getProperty(key).orEmpty()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isEmpty()) {
            properties.remove(key)
        } else {
            properties.setProperty(key, value)
        }
    }

    /**
     * Get value as [Int]
     */
    fun int(defaultValue: Int = 0) = object : ReadWriteProperty<Any?, Int> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            return this@PropertiesValue.getValue(thisRef, property).toIntOrNull() ?: defaultValue
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            this@PropertiesValue.setValue(thisRef, property, value.toString())
        }
    }
}
