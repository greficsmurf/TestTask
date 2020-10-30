package com.example.goratest.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goratest.R
import com.example.goratest.databinding.RecyclerPhotoItemBinding
import com.example.goratest.domain.Photo

class PhotosAdapter(private val bindingComponent: DataBindingComponent) : ListAdapter<Photo, RecyclerView.ViewHolder>(diffUtil){
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<Photo>(){
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerPhotoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_photo_item,
            parent,
            false,
            bindingComponent
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PhotoViewHolder){
            holder.bind(getItem(position))
        }
    }
}

class PhotoViewHolder(val binding: RecyclerPhotoItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(photo: Photo){
        binding.apply {
            this.photo = photo
            executePendingBindings()
        }
    }

}