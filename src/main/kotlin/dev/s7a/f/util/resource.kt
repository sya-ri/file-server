package dev.s7a.f.util

import java.io.File

/**
 * Copy file from resources/[fileName]
 */
fun File.copyFromResource(fileName: String): Boolean {
    return ClassLoader.getSystemResourceAsStream(fileName)?.use {
        outputStream().use { output ->
            it.copyTo(output)
            output.flush()
        }
    } != null
}
