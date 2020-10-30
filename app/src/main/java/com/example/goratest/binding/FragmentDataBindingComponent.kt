package com.example.goratest.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

class FragmentDataBindingComponent(
    val fragment: Fragment
) : DataBindingComponent {
    private val adapters = FragmentBindingAdapters(fragment)
    override fun getFragmentBindingAdapters() = adapters
}