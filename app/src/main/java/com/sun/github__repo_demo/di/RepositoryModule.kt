package com.sun.github__repo_demo.di

import com.sun.github__repo_demo.data.repository.RepoRepository
import com.sun.github__repo_demo.data.repository.RepoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepoRepository(repoRepositoryImpl: RepoRepositoryImpl): RepoRepository
}
