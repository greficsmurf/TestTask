package com.example.goratest.myfakeglide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class FakeGlideCacheManager private constructor(): CacheManager<Bitmap>() {

    companion object{
        private var instance: FakeGlideCacheManager? = null

        fun getInstance(context: Context): FakeGlideCacheManager {
            return when {
                instance != null -> instance!!
                else -> synchronized(this) {
                    if (instance == null) instance = FakeGlideCacheManager().initCache(context) as FakeGlideCacheManager
                    instance!!
                }
            }
        }
    }
    override fun diskGetItem(fileName: String): Bitmap? {
        return try{
            BitmapFactory.decodeStream(FileInputStream(getFileNamePath(fileName)))
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    override fun diskPutItem(fileName: String, item: Bitmap) {
        try {
            val file = File(getFileNamePath(fileName))
            file.createNewFile()
            item.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(file))
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}