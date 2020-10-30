package com.example.goratest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.goratest.repo.HomeRepo
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val homeRepo: HomeRepo
) : ViewModel(){

    val usersResource = homeRepo.getUsersResource().asLiveData()

    val users = usersResource.map {
        it.data
    }

}