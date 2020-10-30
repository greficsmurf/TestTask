package com.example.goratest.binding

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.example.goratest.R
import com.example.goratest.myfakeglide.MyFakeGlide


class FragmentBindingAdapters(val fragment: Fragment) {

    @BindingAdapter("android:setImage")
    fun setImage(view: ImageView, url: String){
        val progressbarDrawable = ContextCompat.getDrawable(fragment.requireContext(), R.drawable.loader)
        MyFakeGlide.with(fragment).load(url).placeHolder(progressbarDrawable).into(view)
    }

//    @BindingAdapter("android:setImageg")
//    fun setImageGlide(view: ImageView, url: String){
//        val glideUrl = GlideUrl(
//            url, LazyHeaders.Builder()
//                .addHeader("User-Agent", fragment.getString(R.string.user_agent_workaround))
//                .build()
//        )
//        val options = RequestOptions().transform(RoundedCorners(16))
//        Glide.with(fragment).load(glideUrl).apply(options).into(view)
//    }
}