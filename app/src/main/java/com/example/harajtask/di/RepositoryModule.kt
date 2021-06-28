package com.example.harajtask.di

import android.content.Context
import com.example.harajtask.repository.IRepository
import com.example.harajtask.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(
        @ApplicationContext appContext: Context
    ) = Repository(appContext) as IRepository
}