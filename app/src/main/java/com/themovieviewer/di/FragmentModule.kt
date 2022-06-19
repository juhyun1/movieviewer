package com.themovieviewer.di

import com.themovieviewer.presentation.ui.nowplaying.NowPlayingFragment
import com.themovieviewer.presentation.ui.popular.MoviePopularFragment
import com.themovieviewer.presentation.ui.toprated.TopRatedFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object FragmentModule {

    @Provides
    fun provideTopRatedFragment(): TopRatedFragment {
        return TopRatedFragment()
    }

    @Provides
    fun provideNowPlayingFragment(): NowPlayingFragment {
        return NowPlayingFragment()
    }

    @Provides
    fun provideMoviePopularFragment(): MoviePopularFragment {
        return MoviePopularFragment()
    }
}
