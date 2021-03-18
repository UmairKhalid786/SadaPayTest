package com.techlads.sadapaytest.di.modules

import android.content.Context
import com.squareup.moshi.Moshi
import com.techlads.sadapaytest.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.di.modules
 */

@InstallIn(SingletonComponent::class)
@Module
class MainModule  {

    @Singleton
    @Provides
    fun providesMoshi() =  Moshi.Builder().build()
}