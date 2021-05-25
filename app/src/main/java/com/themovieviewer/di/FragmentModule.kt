package com.themovieviewer.di

import com.themovieviewer.presentation.ui.gallery.GalleryFragment
import com.themovieviewer.presentation.ui.home.HomeFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {

    @Singleton
    @Provides
    fun provideHomeFragment(): HomeFragment {
        return HomeFragment()
    }

    @Singleton
    @Provides
    fun provideGalleryFragment(): GalleryFragment {
        return GalleryFragment()
    }
}