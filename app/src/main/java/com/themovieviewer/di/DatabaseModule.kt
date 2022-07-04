package com.themovieviewer.di

import android.content.Context
import androidx.room.Room
import com.themovieviewer.core.database.AppDatabase
import com.themovieviewer.core.data.DaoMapper
import com.themovieviewer.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): com.themovieviewer.core.database.AppDatabase {
        return Room
            .databaseBuilder(context, com.themovieviewer.core.database.AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: com.themovieviewer.core.database.AppDatabase): FavoritesMovieDao {
        return appDatabase.favoritesMovieDao()
    }

    @Singleton
    @Provides
    fun provideFavoritesDao(appDatabase: com.themovieviewer.core.database.AppDatabase): FavoritesDao {
        return appDatabase.favoritesDao()
    }

    @Singleton
    @Provides
    fun provideDaoMapper(): DaoMapper {
        return DaoMapper()
    }
}
