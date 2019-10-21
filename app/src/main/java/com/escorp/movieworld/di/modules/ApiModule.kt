package com.escorp.movieworld.di.modules

import com.escorp.movieworld.Application
import com.escorp.movieworld.R
import com.escorp.movieworld.data.api.MovieApi
import com.escorp.movieworld.data.api.RequestInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun providesCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(cache: Cache, application: Application): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().apply {
            cache(cache)
            addInterceptor(logging)
            addNetworkInterceptor(RequestInterceptor(application.getString(R.string.TMDbApiKey)))
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        context: Application
    ): Retrofit {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(context.getString(R.string.serverAdderss))
            .client(okHttpClient)

        return builder.build()
    }

    @Provides
    @Singleton
    internal fun providesMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}