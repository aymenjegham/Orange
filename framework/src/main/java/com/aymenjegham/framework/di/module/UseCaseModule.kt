package com.aymenjegham.framework.di.module


import com.aymenjegham.core.usecase.account.GetAccountsUseCase
import com.aymenjegham.core.usecase.account.GetAccountsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    abstract fun bindGetAllAccountsUseCase(useCase: GetAccountsUseCaseImpl): GetAccountsUseCase

}
