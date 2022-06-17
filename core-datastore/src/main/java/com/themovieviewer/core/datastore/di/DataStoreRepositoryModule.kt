package com.themovieviewer.core.datastore.di

import com.themovieviewer.core.datastore.repository.PreferencesRepository
import com.themovieviewer.core.datastore.repository.PreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataStoreRepositoryModule {

    @Binds
    fun bindPreferencesRepository(impl: PreferencesRepositoryImpl): PreferencesRepository
}