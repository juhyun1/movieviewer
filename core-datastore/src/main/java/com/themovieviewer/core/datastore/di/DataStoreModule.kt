package com.themovieviewer.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    //region protobuf
    //    @Provides
//    @Singleton
//    fun providesUserPreferencesDataStore(
//        @ApplicationContext context: Context,
//        userPreferencesSerializer: UserPreferencesSerializer
//    ): DataStore<UserPreferences> =
//        DataStoreFactory.create(
//            serializer = userPreferencesSerializer,
//            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
//        ) {
//            context.dataStoreFile("user_preferences.pb")
//        }

//    @Provides
//    @Singleton
//    fun providesUserPreferencesDataSource(
//        userPreferences: DataStore<UserPreferences>
//    ): PreferencesDataSource =
//        PreferencesDataSource(
//            userPreferences = userPreferences
//        )
    //endregion

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")