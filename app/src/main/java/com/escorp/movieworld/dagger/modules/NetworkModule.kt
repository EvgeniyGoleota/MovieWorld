package com.escorp.movieworld.dagger.modules

import android.content.Context
import com.escorp.movieworld.R
import com.escorp.movieworld.api.InitializeApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun providesTMDbInitializeApi(retrofit: Retrofit): InitializeApi = retrofit.create(InitializeApi::class.java)

    @Provides
    @Reusable
    fun providesRetrofitInterface(context: Context): Retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.serverAdderss))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}