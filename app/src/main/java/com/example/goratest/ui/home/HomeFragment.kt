package com.example.goratest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goratest.R
import com.example.goratest.adapters.recycler.UsersAdapter
import com.example.goratest.binding.FragmentDataBindingComponent
import com.example.goratest.databinding.FragmentHomeBinding
import com.example.goratest.di.Injectable
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val vm: HomeViewModel by viewModels {
        vmFactory
    }

    private val bindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
                inflater,
                R.layout.fragment_home,
                container,
                false,
                bindingComponent
        )

        val usersAdapter = UsersAdapter()
        vm.users.observe(viewLifecycleOwner, Observer {
            it?.let {
                usersAdapter.submitList(it)
            }
        })
        binding.apply {
            recyclerUsers.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = usersAdapter
            }
            viewModel = vm
            lifecycleOwner = viewLifecycleOwner
        }



        return binding.root
    }
}