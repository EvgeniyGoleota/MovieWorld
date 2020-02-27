package com.escorp.movieworld.network.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@DataScope
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface DataComponent {

    fun getRetrofit(): Retrofit

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): DataComponent
    }
}