package com.example.goratest.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goratest.R
import com.example.goratest.databinding.RecyclerDefaultItemBinding
import com.example.goratest.domain.Album
import com.example.goratest.ui.albums.AlbumsFragmentDirections

class AlbumsAdapter : ListAdapter<Album, RecyclerView.ViewHolder>(diffUtil){
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Album>(){
            override fun areItemsTheSame(oldItem: Album, newItem: Album) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerDefaultItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_default_item,
            parent,
            false
        )

        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AlbumViewHolder){
            holder.bind(getItem(position))
        }
    }


}

class AlbumViewHolder(private val binding: RecyclerDefaultItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(album: Album){
        binding.apply {
            name = album.title
            layout.setOnClickListener {
                it.findNavController().navigate(AlbumsFragmentDirections.actionAlbumsFragmentToPhotosFragment(album.id, album.title))
            }
            executePendingBindings()
        }
    }
}