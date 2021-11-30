package dev.s7a.f.util

/**
 * Use for [kotlin.system.exitProcess]
 */
enum class Statuses {
    /**
     * Illegal value in properties
     */
    IllegalSetting;

    /**
     * Exit code
     */
    val exitCode
        get() = ordinal + 1
}
