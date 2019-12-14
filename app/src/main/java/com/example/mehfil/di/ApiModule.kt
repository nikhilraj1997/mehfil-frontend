package com.example.mehfil.di

import com.example.mehfil.model.PostsApi
import com.example.mehfil.model.PostsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "http://nr-mehfil.herokuapp.com/"

    @Provides
    fun providePostsApi(): PostsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(PostsApi::class.java)
    }

    @Provides
    fun providesPostsService(): PostsService {
        return PostsService()
    }
}