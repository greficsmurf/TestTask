package com.example.goratest.myfakeglide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.io.InputStream


object MyFakeGlide {

    fun with(fragment: Fragment): RequestManager = getRequestManager(fragment.requireContext())

    private fun getRequestManager(context: Context): RequestManager =
        RequestManager(FakeGlideCacheManager.getInstance(context))

}