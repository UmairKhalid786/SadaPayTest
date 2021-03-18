package com.techlads.sadapaytest.di.modules

import android.content.Context
import android.os.Looper
import com.squareup.moshi.Moshi
import com.techlads.sadapaytest.MyApp
import com.techlads.sadapaytest.data.entities.RemoteDataSource
import com.techlads.sadapaytest.data.api.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.di.modules
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class NetModule {

    companion object {

        private const val HTTP_TIMEOUT_S = 30
        private const val HTTP_RESPONSE_CACHE = (10 * 1024 * 1024).toLong()

        @Provides
        @Singleton
        internal fun provideCache(@ApplicationContext context: Context): Cache {
            //checkThread()
            return Cache(context.cacheDir, HTTP_RESPONSE_CACHE)
        }


        private fun checkThread() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw IllegalStateException("Don't do this on main thread.")
            }
        }

        @Provides
        @Singleton
        internal fun provideOkHttpClient(
            // cache: Cache,
        ): OkHttpClient {
            // checkThread()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder =
                OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(HTTP_TIMEOUT_S.toLong(), TimeUnit.SECONDS)
                    .readTimeout(HTTP_TIMEOUT_S.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(HTTP_TIMEOUT_S.toLong(), TimeUnit.SECONDS)
            //.cache(cache)
            return builder.build()
        }


        @Singleton
        @Provides
        fun provideRetrofit(moshi: Moshi, client : OkHttpClient) : Retrofit = Retrofit.Builder().client(client)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        @Singleton
        @Provides
        fun provideRemoteDataSource(api: GithubApi) = RemoteDataSource(api)


        @Provides
        @Singleton
        fun provideFlickerApi(retrofit: Retrofit): GithubApi {
            return retrofit.create(GithubApi::class.java)
        }
    }
}