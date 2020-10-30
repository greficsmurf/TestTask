package com.example.goratest.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goratest.R
import com.example.goratest.databinding.FragmentHomeBinding
import com.example.goratest.databinding.RecyclerDefaultItemBinding
import com.example.goratest.domain.User
import com.example.goratest.ui.home.HomeFragmentDirections

class UsersAdapter : ListAdapter<User, RecyclerView.ViewHolder>(diffUtil){
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.userId == newItem.userId
            override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerDefaultItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recycler_default_item,
                parent,
                false
        )

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is UserViewHolder)
            holder.bind(getItem(position))
    }
}

class UserViewHolder(private val binding: RecyclerDefaultItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(user: User){
        binding.apply {
            name = user.name
            layout.setOnClickListener {
                it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAlbumsFragment(user.userId, user.userName))
            }
            executePendingBindings()
        }
    }
}