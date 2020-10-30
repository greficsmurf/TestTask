package com.example.goratest.vo

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception

abstract class NetworkResource<ApiT, DomainT> {
    private val result = flow<Resource<DomainT>> {
        emit(Resource.loading(null))
        try{
            emit(Resource.loaded(toDomainModel(fetch())))
        }catch (e: Exception){
            Timber.d(e.message)
            emit(Resource.failed(null, e.message ?: ""))
        }
    }

    fun asFlow() = result
    fun asLiveData() = result.asLiveData()

    abstract suspend fun fetch(): ApiT
    abstract fun toDomainModel(data: ApiT): DomainT
}