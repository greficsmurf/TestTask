package com.example.goratest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goratest.ui.albums.AlbumsViewModel
import com.example.goratest.ui.home.HomeViewModel
import com.example.goratest.ui.photos.PhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class ViewModelModule{

    @Binds
    @IntoSet
    abstract fun bindHomeViewModel(vm: HomeViewModel): ViewModel

    @Binds
    @IntoSet
    abstract fun bindAlbumsViewModel(vm: AlbumsViewModel): ViewModel

    @Binds
    @IntoSet
    abstract fun bindPhotosViewModel(vm: PhotosViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}