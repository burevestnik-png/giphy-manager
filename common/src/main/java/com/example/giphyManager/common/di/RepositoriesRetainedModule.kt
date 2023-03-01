package com.example.giphyManager.common.di

import com.example.giphyManager.common.data.repositories.GifRepositoryImpl
import com.example.giphyManager.common.domain.repositories.GifRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class RepositoriesRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindGifRepository(repository: GifRepositoryImpl): GifRepository
}