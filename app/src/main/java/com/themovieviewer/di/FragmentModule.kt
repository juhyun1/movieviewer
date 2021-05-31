package com.themovieviewer.di

import com.themovieviewer.presentation.ui.gallery.GalleryFragment
import com.themovieviewer.presentation.ui.home.HomeFragment
import com.themovieviewer.presentation.ui.slideshow.SlideshowFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {

    @Provides
    fun provideHomeFragment(): HomeFragment {
        return HomeFragment()
    }

    @Provides
    fun provideGalleryFragment(): GalleryFragment {
        return GalleryFragment()
    }

    @Provides
    fun provideSlideshowFragment(): SlideshowFragment {
        return SlideshowFragment()
    }
}
