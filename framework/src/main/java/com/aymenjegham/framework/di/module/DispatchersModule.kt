package com.aymenjegham.framework.di.module

import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import com.aymenjegham.framework.di.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {


    @Binds
    @Singleton
    abstract fun bindDispatchers(dispatchers: DispatcherProviderImpl): DispatcherProvider
}