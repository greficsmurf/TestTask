package com.example.goratest.di

import com.example.goratest.MainActivity
import com.example.goratest.ui.albums.AlbumsFragment
import com.example.goratest.ui.home.HomeFragment
import com.example.goratest.ui.photos.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeAlbumsFragment(): AlbumsFragment

    @ContributesAndroidInjector
    abstract fun contributePhotosFragment(): PhotosFragment
}