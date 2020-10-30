package com.example.goratest.myfakeglide

import android.graphics.Bitmap
import android.widget.ImageView
import okhttp3.ResponseBody
import java.lang.Exception

interface DataListener{
    fun onResponse(bitmap: Bitmap, loadTarget: ImageView)
    fun onError(e: Exception)
}