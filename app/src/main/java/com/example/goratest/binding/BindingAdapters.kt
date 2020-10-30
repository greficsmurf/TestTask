package com.example.goratest.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.goratest.vo.ResourceStatus

@BindingAdapter("android:isVisible")
fun setVisibility(view: View, isVisible: Boolean){
    if(isVisible)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("android:isVisible")
fun isVisible(view: View, resourceStatus: ResourceStatus?){
    when(resourceStatus){
        ResourceStatus.LOADED -> setVisibility(view, false)
        ResourceStatus.FAILED -> setVisibility(view, false)
        ResourceStatus.LOADING -> setVisibility(view, true)
        null -> setVisibility(view, false)
    }
}

