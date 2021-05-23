package com.themovieviewer.di

import com.themovieviewer.presentation.paging.TopRatedAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object PagingModule {

    @Provides
    fun provideTopRatedAdapter(): TopRatedAdapter {
        return TopRatedAdapter()
    }
}