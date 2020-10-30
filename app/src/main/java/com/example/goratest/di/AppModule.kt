package com.example.goratest.di

import android.app.Application
import androidx.room.Room
import com.example.goratest.api.ApiService
import com.example.goratest.db.AppDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    fun provideDatabase(app: Application) = Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "AppDatabase"
    ).build()


    @Singleton
    @Provides
    fun provideApiService(): ApiService{
        @Suppress("SpellCheckingInspection")
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(ApiService::class.java)
    }

}