package dev.s7a.f.util.properties.types

import dev.s7a.f.util.properties.PropertiesValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get value as [Boolean]
 */
fun PropertiesValue.boolean(defaultValue: Boolean = false) = object : ReadWriteProperty<Any?, Boolean> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return this@boolean.getValue(thisRef, property)?.toBooleanStrictOrNull() ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        this@boolean.setValue(thisRef, property, value.toString())
    }
}
