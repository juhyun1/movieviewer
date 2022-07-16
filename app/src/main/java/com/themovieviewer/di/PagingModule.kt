package com.themovieviewer.di

import com.themovieviewer.presentation.paging.ActingAdapter
import com.themovieviewer.presentation.paging.CreditsAdapter
import com.themovieviewer.presentation.paging.MovieOneRowAdapter
import com.themovieviewer.presentation.paging.MovieRecommendationsAdapter
import com.themovieviewer.presentation.paging.MovieTwoColumnsAdapter
import com.themovieviewer.presentation.paging.VideosAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object PagingModule {

    @Provides
    fun provideTopRatedAdapter(): MovieTwoColumnsAdapter {
        return MovieTwoColumnsAdapter()
    }

    @Provides
    fun provideOneRowAdapter(): MovieOneRowAdapter {
        return MovieOneRowAdapter()
    }

    @Provides
    fun provideCreditsAdapter(): CreditsAdapter {
        return CreditsAdapter()
    }

    @Provides
    fun provideMovieRecommendationsAdapter(): MovieRecommendationsAdapter {
        return MovieRecommendationsAdapter()
    }

    @Provides
    fun videosAdapter(): VideosAdapter {
        return VideosAdapter()
    }

    @Provides
    fun actingAdapter(): ActingAdapter {
        return ActingAdapter()
    }
}
