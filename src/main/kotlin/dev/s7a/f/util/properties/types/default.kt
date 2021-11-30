package dev.s7a.f.util.properties.types

import dev.s7a.f.util.properties.PropertiesValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get value as [String]
 */
fun PropertiesValue.default(defaultValue: String) = object : ReadWriteProperty<Any?, String> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return this@default.getValue(thisRef, property) ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this@default.setValue(thisRef, property, value)
    }
}
