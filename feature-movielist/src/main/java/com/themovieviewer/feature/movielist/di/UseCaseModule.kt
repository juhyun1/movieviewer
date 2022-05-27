package com.themovieviewer.feature.movielist.di

import com.themovieviewer.core.model.usecase.GetNowPlayingPagerUseCase
import com.themovieviewer.feature.movielist.usecase.GetNowPlayingPagerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface UseCaseModule2 {

    @Binds
    fun bindGetNowPlayingPagerUseCase(impl: GetNowPlayingPagerUseCaseImpl): GetNowPlayingPagerUseCase
}
