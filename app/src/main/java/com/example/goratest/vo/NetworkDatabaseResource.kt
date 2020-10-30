package com.example.goratest.vo

import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception

abstract class NetworkDatabaseResource<ApiT, DbT, DomainT> {
    private val result = flow<Resource<DomainT>> {
        emit(Resource.loading(null))
        coroutineScope {
            try{
                fetchDb().collect { dbData ->
                    if(shouldFetchApi(dbData)){
                        val domainData = toDomainModelApi(fetchApi())
                        onDbSave(domainData)
                    }
                    else
                        emit(Resource.loaded(toDomainModelDb(dbData)))
                }
            }catch (e: Exception){
                emit(Resource.failed(null, e.message ?: ""))
                cancel()
            }
        }
    }

    fun toFlow() = result

    abstract suspend fun fetchDb(): Flow<DbT>
    abstract fun toDomainModelDb(data: DbT): DomainT

    abstract suspend fun fetchApi(): ApiT
    abstract fun toDomainModelApi(data: ApiT): DomainT

    abstract fun shouldFetchApi(data: DbT?): Boolean
    abstract fun onDbSave(data: DomainT)
}