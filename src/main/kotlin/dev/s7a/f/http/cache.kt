package dev.s7a.f.http

import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Cache data
 */
data class Cache(val file: File, val etag: String)

/**
 * Cache list
 */
class CacheList {
    /**
     * Cache list (key: path)
     */
    private val cacheList = ConcurrentHashMap<String, Cache>()

    /**
     * Get cache
     */
    fun get(path: String): Cache? {
        return cacheList[path]?.takeIf { it.file.exists() }
    }

    /**
     * Update cache
     */
    fun update(path: String, file: File, etag: String?) {
        if (etag != null) {
            cacheList[path] = Cache(file, etag)
        } else {
            cacheList.remove(path)
        }
    }
}
