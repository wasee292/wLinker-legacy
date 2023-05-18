package com.wasee292.wlinker.legacy.di

import android.content.Context
import androidx.room.Room
import com.wasee292.wlinker.legacy.db.AppDatabase
import com.wasee292.wlinker.legacy.db.AppDatabaseConverters
import com.wasee292.wlinker.legacy.db.DbConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
        appDatabaseConverters: AppDatabaseConverters,
    ) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        DbConstant.dbName,
    ).addTypeConverter(appDatabaseConverters).build()

    @Singleton
    @Provides
    fun provideLinkDao(appDatabase: AppDatabase) = appDatabase.linkDao()

    @Singleton
    @Provides
    fun provideTagDao(appDatabase: AppDatabase) = appDatabase.tagDao()

    @Singleton
    @Provides
    fun provideAppDatabaseConverter(json: Json): AppDatabaseConverters = AppDatabaseConverters(json)

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideJson() = Json {
        encodeDefaults = true; isLenient = true; ignoreUnknownKeys = true; explicitNulls = true;
    }
}
