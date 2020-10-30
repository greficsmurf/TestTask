package com.example.goratest.ui.albums

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
import com.example.goratest.adapters.recycler.AlbumsAdapter
import com.example.goratest.base.BaseFragment
import com.example.goratest.databinding.FragmentAlbumsBinding
import javax.inject.Inject

class AlbumsFragment : BaseFragment(){

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val vm: AlbumsViewModel by viewModels {
        vmFactory
    }

    private val args: AlbumsFragmentArgs by navArgs<AlbumsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAlbumsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_albums,
            container,
            false
        )
        vm.setUserId(args.userId)
        val albumsAdapter = AlbumsAdapter()

        binding.apply {
            recyclerAlbums.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = albumsAdapter
            }
            viewModel = vm
            lifecycleOwner = viewLifecycleOwner
        }

        vm.albums.observe(viewLifecycleOwner, Observer {
            albumsAdapter.submitList(it)
        })

        return binding.root
    }

}