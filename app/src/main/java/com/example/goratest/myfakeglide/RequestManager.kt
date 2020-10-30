package com.example.goratest.myfakeglide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import timber.log.Timber
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class RequestManager(
    private val cacheManager: CacheManager<Bitmap>
) : LifecycleObserver{

    private val mClient = OkHttpClient()
    private var mRequest: Request? = null
    private val mListeners = mutableListOf<DataListener>()
    private var placeHolder: Drawable? = null
    private lateinit var mLoadTarget: ImageView

    fun load(url: String) : RequestManager{
        mRequest = buildRequest(url)
        return this
    }

    private fun fetch(){
        if(mRequest == null)
            return
        initPlaceholder()
        val cachedBitmap = getCachedBitmap(mRequest!!.url().toString())

        if(cachedBitmap != null){
            fillTarget(cachedBitmap)
            return
        }

        mClient.newCall(mRequest!!).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Timber.d("Error occurred")
                e.printStackTrace()
                for (listener in mListeners)
                    listener.onError(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body() ?: throw FileNotFoundException("Endpoint is empty")

                val bitmap = decodeStream(data.byteStream())

                fillTarget(bitmap)
                cacheBitmap(mRequest!!.url().toString(), bitmap)
            }
        })
    }

    fun placeHolder(drawable: Drawable?): RequestManager{
        placeHolder = drawable
        return this
    }


    fun addListener(listener: DataListener){
        mListeners.add(listener)
    }

    fun into(view: ImageView){
        mLoadTarget = view
        fetch()
    }

    private fun buildRequest(url: String): Request = Request.Builder()
        .url(url)
        .build()


    private fun decodeStream(stream: InputStream): Bitmap = BitmapFactory.decodeStream(stream)

    private fun fillTarget(bitmap: Bitmap){
        callListeners(bitmap)
        val processedBmp = BitmapPaint.drawShadow(bitmap, 5F)
        CoroutineScope(Dispatchers.Main).launch {
            mLoadTarget.setImageBitmap(processedBmp)
        }
    }
    private fun cacheBitmap(name: String, bitmap: Bitmap){
        cacheManager.putItem(mRequest!!.url().toString(), bitmap)
        CoroutineScope(Dispatchers.IO).launch{
            cacheManager.diskPutItem(mRequest!!.url().toString(), bitmap)
        }
    }
    private fun getCachedBitmap(name: String): Bitmap?{
        val memoryCached = cacheManager.getItem(name)
        if(memoryCached != null)
            return memoryCached

        return cacheManager.diskGetItem(name)
    }
    private fun callListeners(bitmap: Bitmap){
        for (listener in mListeners)
            listener.onResponse(bitmap, mLoadTarget)
    }

    private fun initPlaceholder() {
        placeHolder?.let {
            mLoadTarget.setImageDrawable(it)
            try {
                (it as AnimatedVectorDrawable).start()
            }catch (e: Exception){}
        }
    }
}