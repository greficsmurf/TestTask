package com.example.goratest.myfakeglide

import android.content.Context
import android.util.LruCache
import com.example.goratest.R

abstract class CacheManager<CacheItem>{
    private lateinit var mMemoryCache: LruCache<String, CacheItem>
    private lateinit var mDiskCacheDir: String
    private val clearRegex = "[^A-Za-z0-9]".toRegex()

    open fun getItem(key: String): CacheItem? = mMemoryCache.get(key)
    open fun putItem(key: String, item: CacheItem): CacheItem? = mMemoryCache.put(key, item)

    abstract fun diskGetItem(fileName: String): CacheItem?
    abstract fun diskPutItem(fileName: String, item: CacheItem)

    fun getFileNamePath(path: String) = "$mDiskCacheDir/${path.replace(clearRegex, "")}"

    fun initCache(context: Context): CacheManager<CacheItem>{
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        mMemoryCache = LruCache(maxMemory / 8)

        mDiskCacheDir = "${context.cacheDir.path}/"
        return this
    }



}