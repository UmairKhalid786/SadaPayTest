package com.techlads.sadapaytest.di.modules

import com.techlads.sadapaytest.data.repository.MainRepository
import com.techlads.sadapaytest.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.di.modules
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideMainRepository(mainRepository: MainRepositoryImpl): MainRepository
}