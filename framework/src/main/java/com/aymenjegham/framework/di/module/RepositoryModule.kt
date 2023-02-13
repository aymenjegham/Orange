package com.aymenjegham.framework.di.module


import com.aymenjegham.core.data.repository.account.AccountRepository
import com.aymenjegham.framework.data.repositoryImpl.AccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Reusable
    fun bindAccountRepository(repository: AccountRepositoryImpl): AccountRepository


}