package dev.s7a.f.util.properties.types

import dev.s7a.f.fileProvider.FileProvider
import dev.s7a.f.fileProvider.LocalFileProvider
import dev.s7a.f.util.properties.PropertiesValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Get value as [FileProvider]
 */
fun PropertiesValue.fileProvider(defaultValue: FileProvider = LocalFileProvider) = object : ReadWriteProperty<Any?, FileProvider> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): FileProvider {
        return this@fileProvider.getValue(thisRef, property)?.let(FileProvider::getByName) ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: FileProvider) {
        this@fileProvider.setValue(thisRef, property, value.name)
    }
}
