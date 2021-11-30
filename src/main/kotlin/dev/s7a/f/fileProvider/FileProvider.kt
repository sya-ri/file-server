package dev.s7a.f.fileProvider

/**
 * Get files
 */
sealed interface FileProvider {
    /**
     * name
     */
    val name: String

    /**
     * current settings
     */
    val settings: List<String>

    companion object {
        /**
         * Get [FileProvider] by name
         */
        fun getByName(name: String) = FileProvider::class.sealedSubclasses.firstOrNull {
            it.simpleName.equals(name, true)
        }?.objectInstance
    }
}
