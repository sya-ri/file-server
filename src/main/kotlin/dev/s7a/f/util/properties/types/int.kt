package dev.s7a.f.util.properties.types

import dev.s7a.f.util.properties.PropertiesValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get value as [Int]
 */
fun PropertiesValue.int(defaultValue: Int = 0) = object : ReadWriteProperty<Any?, Int> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return this@int.getValue(thisRef, property)?.toIntOrNull() ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        this@int.setValue(thisRef, property, value.toString())
    }
}
