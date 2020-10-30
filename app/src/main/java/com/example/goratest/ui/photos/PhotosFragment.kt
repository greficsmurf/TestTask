package com.example.goratest.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goratest.R
import com.example.goratest.adapters.recycler.PhotosAdapter
import com.example.goratest.base.BaseFragment
import com.example.goratest.binding.FragmentDataBindingComponent
import com.example.goratest.databinding.FragmentPhotosBinding
import javax.inject.Inject

class PhotosFragment : BaseFragment(){

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val vm: PhotosViewModel by viewModels {
        vmFactory
    }
    private val bindingComponent = FragmentDataBindingComponent(this)

    private val args: PhotosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPhotosBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_photos,
            container,
            false, bindingComponent
        )

        val photosAdapter = PhotosAdapter(bindingComponent)

        binding.apply {
            recyclerPhotos.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = photosAdapter
            }
        }

        vm.setAlbumId(args.albumId)

        vm.photos.observe(viewLifecycleOwner, Observer {
            photosAdapter.submitList(it)
        })

        return binding.root
    }
}